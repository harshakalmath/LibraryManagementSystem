package com.application.library.controller;

import com.application.library.ObserverHelper;
import com.application.library.observer.Tracker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for the tracker API.
 */
@RestController
@RequestMapping("/api")
public class TrackerController {

    ObserverHelper helper = ObserverHelper.getInstance();

    @RequestMapping(value="/tracker", method= RequestMethod.GET)
    public String getTrackerInfo() {
        Tracker tracker = Tracker.getInstance();
        org.json.JSONObject obj = new org.json.JSONObject();
        obj.put("books_lent", tracker.getNumberOfBooksLent());
        return obj.toString();
    }
}
