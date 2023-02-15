package hr.smatijasevic.urlservis.service;

import hr.smatijasevic.urlservis.entity.UrlRequest;
import hr.smatijasevic.urlservis.entity.Url;
import hr.smatijasevic.urlservis.entity.UrlResponse;
import hr.smatijasevic.urlservis.security.user.Account;

import java.util.Map;

public interface UrlService {
    String convertToShortUrl(UrlRequest urlRequest, Account acc);
    Url getOriginalUrl(String shortUrl);
    UrlResponse concatenateUrl(String shortUrl);
    void updateStatistics(Url url);
    Map<String, Long> getStatistics(String accountId);
}
