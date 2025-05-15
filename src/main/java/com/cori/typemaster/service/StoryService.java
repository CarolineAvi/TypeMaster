package com.cori.typemaster.service;

import com.cori.typemaster.model.Story;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for loading stories from bundled JSON resources.
 * <p>
 * Supplies stories for selection and gameplay by reading and parsing categorized JSON resource files
 * from the application's resources directory.
 * </p>
 * 
 * <h2>Main Responsibilities</h2>
 * <ul>
 *   <li>Loads and parses all available stories grouped by difficulty.</li>
 *   <li>Provides utility methods to retrieve all stories as a {@code List<Story>}.</li>
 *   <li>Acts as a data provider for controllers that handle story selection.</li>
 * </ul>
 * 
 * <h3>Typical usage</h3>
 * <pre>
 *   StoryService service = new StoryService();
 *   List&lt;Story&gt; stories = service.loadAllStories();
 * </pre>
 *
 * <h3>Implementation Notes</h3>
 * <ul>
 *   <li>Utilizes resource files in <code>/com/cori/typemaster/data/stories/&lt;difficulty&gt;/&lt;difficulty&gt;_stories.json</code>.</li>
 *   <li>Returns an empty list if a file is missing or on parse errors.</li>
 * </ul>
 * 
 * This class does not cache results; each call reloads story data from resources.
 * 
 * @author YourName
 */
public class StoryService {
    /** Array of supported difficulty labels/categories (folder and file prefix). */
    private static final String[] DIFFICULTIES = {"easy", "medium", "hard"};

    /**
     * Loads all stories across all supported difficulties.
     * <ul>
     *   <li>Iterates over supported difficulties, loading their story lists.</li>
     *   <li>Merges results into a flat list.</li>
     * </ul>
     * 
     * @return list of all stories available in the application
     */
    public List<Story> loadAllStories() {
        List<Story> stories = new ArrayList<>();
        for (String diff : DIFFICULTIES) {
            String fileName = String.format("/com/cori/typemaster/data/stories/%s/%s_stories.json", diff, diff);
            stories.addAll(loadStoriesFromJson(fileName));
        }
        return stories;
    }

    /**
     * Loads stories from a specific JSON resource.
     * <ul>
     *   <li>If the resource is not found or unreadable, returns an empty list.</li>
     *   <li>Expects the JSON to be an array of objects (<code>title</code>, <code>content</code>, <code>difficulty</code> fields).</li>
     *   <li>On error, exceptions are caught and ignored.</li>
     * </ul>
     *
     * @param resourcePath the absolute path to the JSON resource file in application resources
     * @return list of loaded {@link Story} objects (possibly empty)
     */
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