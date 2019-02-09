build:
	gradle build
.PHONY: build

lint:
	gradle ktlint
.PHONY: lint

test:
	gradle test
	open "./build/reports/tests/test/index.html"
.PHONY: test
