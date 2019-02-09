build:
	./gradlew build
.PHONY: build

lint:
	./gradlew ktlint
.PHONY: lint

test:
	./gradlew test
	open "./build/reports/tests/test/index.html"
.PHONY: test
