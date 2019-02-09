build:
	./gradlew build
.PHONY: build

coverage:
	./gradlew jacocoTestReport
	open "./build/reports/jacoco/test/html/index.html"
.PHONY: coverage

lint:
	./gradlew ktlint
.PHONY: lint

test:
	./gradlew test
	open "./build/reports/tests/test/index.html"
.PHONY: test
