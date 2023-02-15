package hr.smatijasevic.urlservis.service;

import hr.smatijasevic.urlservis.entity.UrlRequest;
import hr.smatijasevic.urlservis.entity.Url;
import hr.smatijasevic.urlservis.entity.UrlResponse;
import hr.smatijasevic.urlservis.repository.UrlRepository;
import hr.smatijasevic.urlservis.security.user.Account;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    public static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int BASE_62 = ALLOWED_CHARACTERS.length();
    private final String BASE_URL = "http://localhost:8080";

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public String convertToShortUrl(UrlRequest urlRequest, Account acc) {
        Url url = new Url();
        url.setLongUrl(urlRequest.getLongUrl());
        url.setRedirectType(urlRequest.getRedirectType() != null ? urlRequest.getRedirectType() : 302);
        url.setCreatedDate(new Date());
        url.setNumberOfClicks(0L);
        url.setAccount(acc);

        Url savedUrl = urlRepository.save(url);

        return encodeUrl(savedUrl.getId());
    }

    @Override
    public Url getOriginalUrl(String shortUrl) {
        Long id = decodeUrl(shortUrl);

        Optional<Url> url = urlRepository.findById(id);
        if (url.isEmpty()) {
            throw new EntityNotFoundException("There is no such url");
        }

        return url.get();
    }

    @Override
    public UrlResponse concatenateUrl(String shortUrl) {
        return new UrlResponse(BASE_URL + "/urlshort/short/" + shortUrl);
    }

    @Override
    public void updateStatistics(Url url) {
        url.setNumberOfClicks(url.getNumberOfClicks() + 1);
        urlRepository.save(url);
    }

    @Override
    public Map<String, Long> getStatistics(String accountId) {
        Map<String, Long> map = new HashMap<>();
        List<Url> urls;
        if (accountId != null) {
            Optional<List<Url>> optionalUrls = urlRepository.findByAccountId(accountId);
            if (optionalUrls.get().isEmpty()) {
                throw new EntityNotFoundException("There is no such account.");
            }
            urls = optionalUrls.get();
        } else {
            urls = urlRepository.findAll();
        }

        urls.forEach(url -> {
            Long value;
            if (map.get(url.getLongUrl()) != null) {
                value = map.get(url.getLongUrl()) + url.getNumberOfClicks();
            } else {
                value = url.getNumberOfClicks();
            }
            map.put(url.getLongUrl(), value);
        });

        return map;
    }

    private String encodeUrl(Long urlId){
        var encodedString = new StringBuilder();

        if(urlId == 0) {
            return String.valueOf(ALLOWED_CHARACTERS.charAt(0));
        }

        while (urlId > 0) {
            encodedString.append(ALLOWED_CHARACTERS.charAt((int) (urlId % BASE_62)));
            urlId = urlId / BASE_62;
        }

        return encodedString.reverse().toString();
    }

    private long decodeUrl(String shortUrl) {
        char[] characters = shortUrl.toCharArray();
        int length = characters.length;

        int decoded = 0;

        int counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += ALLOWED_CHARACTERS.indexOf(characters[i]) * Math.pow(BASE_62, length - counter);
            counter++;
        }
        return decoded;
    }
}
