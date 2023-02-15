package hr.smatijasevic.urlservis.controller;


import hr.smatijasevic.urlservis.entity.UrlRequest;
import hr.smatijasevic.urlservis.entity.Url;
import hr.smatijasevic.urlservis.entity.UrlResponse;
import hr.smatijasevic.urlservis.security.user.Account;
import hr.smatijasevic.urlservis.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/urlshort")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/register")
    public ResponseEntity<UrlResponse> convert(@RequestBody UrlRequest urlRequest, Authentication auth) {
        Account acc = (Account) auth.getPrincipal();
        String shortUrl = urlService.convertToShortUrl(urlRequest, acc);

        return ResponseEntity.status(urlRequest.getRedirectType())
                             .body(urlService.concatenateUrl(shortUrl));
    }

    @GetMapping("/short/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        Url url = urlService.getOriginalUrl(shortUrl);
        urlService.updateStatistics(url);

        return ResponseEntity.status(url.getRedirectType())
                .location(URI.create(url.getLongUrl()))
                .build();
    }

    @GetMapping(value = "/statistic")
    public ResponseEntity<Map<String, Long>> statistics(@RequestParam(required = false) String accountId) {
        Map<String, Long> map = urlService.getStatistics(accountId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(map);
    }
}
