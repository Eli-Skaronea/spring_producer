# spring_producer
A Spring REST endpoint that produces data into a Kafka stream. This is all practice for learning kubernetes and integrating CI/CD

The process to deploy is:
Github --> Jenkins --> Build/Push Docker image --> Deploy helm package --> Push new helm package
