package com.telstra.codechallenge.githubapitest;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GitHubApiTestController {
	@Autowired
	RestTemplate restTemplate;

	@Value("${old.user.url}")
	String oldUsersUrl;

	@Value("${starred.user.url}")
	String starrdUserUrl;

	@Value("${starred.user.url.param}")
	String starrdUserParam;

	@RequestMapping(path = "/oldusers", method = RequestMethod.GET)
	public List<GitHubTestUser> oldUsers(@RequestParam(value = "maxusers") String maxusers) {

		JSONArray jsonArray;

		List<GitHubTestUser> userList = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(headers);
		headers.add("Accept", "application/vnd.github.preview");

		int maxCount = 1;
		if (StringUtils.isNumeric(maxusers))
			maxCount = Integer.parseInt(maxusers);

		String users = restTemplate.exchange(oldUsersUrl, HttpMethod.GET, entity, String.class).getBody();

		jsonArray = new JSONObject(users).getJSONArray("items");

		maxCount = maxcountCheck(maxCount, jsonArray.length());

		return userList = mapOldUsers(userList, jsonArray, maxCount);
	}

	@RequestMapping(path = "/stardusers", method = RequestMethod.GET)
	public List<GitHubApiTestStarrdUser> StarUsers(@RequestParam(value = "maxusers") String maxusers) {

		JSONArray jsonArray;
		String url = starrdUserUrl + LocalDate.now().minus(Period.ofDays(7)).toString() + starrdUserParam;

		List<GitHubApiTestStarrdUser> userList = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(headers);
		headers.add("Accept", "application/vnd.github.preview");

		int maxCount = 1;
		if (StringUtils.isNumeric(maxusers))
			maxCount = Integer.parseInt(maxusers);

		String users = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

		jsonArray = new JSONObject(users).getJSONArray("items");

		maxCount = maxcountCheck(maxCount, jsonArray.length());

		return userList = mapStardUsers(userList, jsonArray, maxCount);

	}

	private int maxcountCheck(int maxCount, int arrLength) {
		if (maxCount > arrLength) {
			return arrLength;
		} else
			return maxCount;

	}

	private List<GitHubTestUser> mapOldUsers(List<GitHubTestUser> userList, JSONArray jsonArray, int maxCount) {

		for (int i = 0; i < maxCount; i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			GitHubTestUser user = new GitHubTestUser();
			user.setId(jsonObj.get("id").toString());
			user.setLogin(jsonObj.get("login").toString());
			user.setHtmlUrl(jsonObj.get("html_url").toString());
			userList.add(user);
		}
		return userList;
	}

	private List<GitHubApiTestStarrdUser> mapStardUsers(List<GitHubApiTestStarrdUser> userList, JSONArray jsonArray,
			int maxCount) {

		for (int i = 0; i < maxCount; i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			GitHubApiTestStarrdUser user = new GitHubApiTestStarrdUser();
			user.setHtml_url(jsonObj.get("html_url").toString());
			user.setWatchers_count(jsonObj.get("watchers_count").toString());
			user.setLanguage(jsonObj.get("language").toString());
			user.setDescription(jsonObj.get("description").toString());
			user.setName(jsonObj.get("name").toString());

			userList.add(user);
		}
		return userList;
	}
}
