package com.telstra.codechallenge.githubapitest;

public class GitHubApiTestStarrdUser {

	private String html_url;
	private String watchers_count;
	private String language;
	private String description;
	private String name;
	public GitHubApiTestStarrdUser() {};
	public GitHubApiTestStarrdUser(String html_url, String watchers_count, String language, String description,
			String name) {
		super();
		this.html_url = html_url;
		this.watchers_count = watchers_count;
		this.language = language;
		this.description = description;
		this.name = name;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getWatchers_count() {
		return watchers_count;
	}

	public void setWatchers_count(String watchers_count) {
		this.watchers_count = watchers_count;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GitHubTestStardUser [html_url=" + html_url + ", watchers_count=" + watchers_count + ", language="
				+ language + ", description=" + description + ", name=" + name + "]";
	}

}
