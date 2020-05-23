Feature: testing old users api up and returning requested number of users

  Scenario: Is the hello uri available and functioning
    Given url microserviceUrl
    And path '/stardusers'
    And param maxusers = 3
	When method GET
    Then status 200
