package com.telstra.codechallenge.githuabitest;

public class GitHubTestUser {

	private String id;
	private String login;
	private String html_url;

	public GitHubTestUser() {
	}

	public GitHubTestUser(String id, String login, String html_url) {
		super();
		this.id = id;
		this.login = login;
		this.html_url = html_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtmlUrl(String html_url) {
		this.html_url = html_url;
	}

	@Override
	public String toString() {
		return "GitHubTestUser [id=" + id + ", login=" + login + ", html_url=" + html_url + "]";
	};

}
