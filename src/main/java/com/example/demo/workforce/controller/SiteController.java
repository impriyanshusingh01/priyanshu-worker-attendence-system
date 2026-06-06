package com.example.demo.workforce.controller;

import com.example.demo.workforce.entity.Site;
import com.example.demo.workforce.io.request.SiteRequest;
import com.example.demo.workforce.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping
    public Site createSite(
            @RequestBody SiteRequest request) {

        return siteService.createSite(request);
    }

    @GetMapping("/{id}")
    public Site getSite(
            @PathVariable Long id) {

        return siteService.getSite(id);
    }
}
