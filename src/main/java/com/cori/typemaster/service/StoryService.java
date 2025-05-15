package com.cori.typemaster.service;

import com.cori.typemaster.model.Story;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StoryService {
    private static final String[] DIFFICULTIES = {"easy", "medium", "hard"};

    public List<Story> loadAllStories() {
        List<Story> stories = new ArrayList<>();
        for (String diff : DIFFICULTIES) {
            String fileName = String.format("/com/cori/typemaster/data/stories/%s/%s_stories.json", diff, diff);
            stories.addAll(loadStoriesFromJson(fileName));
        }
        return stories;
    }

    private List<Story> loadStoriesFromJson(String resourcePath) {
        List<Story> result = new ArrayList<>();
        try (InputStream input = getClass().getResourceAsStream(resourcePath)) {
            if (input == null) {
                return result;
            }
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            JSONArray arr = new JSONArray(sb.toString());
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                result.add(new Story(
                        obj.getString("title"),
                        obj.getString("content"),
                        obj.getString("difficulty")
                ));
            }
        } catch (Exception ignored) {}
        return result;
    }
}