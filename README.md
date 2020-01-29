#ClearScoreAssessment
The app receives an API request with the users information on port 8080, retrieves information from two credit card providers and returns a list
of credit cards sorted by a score that with the highest being the recommended one.

#Setup
Run the start.bash file located in the target folder.  It runs the jar compiled by spring boot.
I was unable to configure the environment variables in time, so the default port is 8080, and the provider endpoints have been added as environment
variables inside of te springboot app.