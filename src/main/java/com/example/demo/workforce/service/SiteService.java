package com.example.demo.workforce.service;

import com.example.demo.workforce.entity.Site;
import com.example.demo.workforce.io.request.SiteRequest;
import com.example.demo.workforce.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;

    public Site createSite(SiteRequest request) {

        Site site = new Site();

        site.setSiteName(request.getSiteName());
        site.setLocation(request.getLocation());
        site.setActive(request.getActive());

        return siteRepository.save(site);
    }

    public Site getSite(Long id) {

        return siteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Site not found"));
    }
}
