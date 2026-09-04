# Intelligent Company Qualification & Ranking System

A lightweight, high-performance Java application designed to parse, qualify, score, and rank companies from JSONL datasets using heuristic intent matching and Jackson parsing.

## Features
- **Robust JSON Parsing:** Reads line-by-line `companies.jsonl` files safely using Jackson `JsonNode`.
- **Hybrid Scoring Engine:** Evaluates keywords across company names, addresses, descriptions, and core offerings.
- **Fast Execution:** Operates entirely via Java Streams in milliseconds without heavy LLM costs.

## Getting Started
1. Clone the repository: `git clone https://github.com/costibaicu/company-qualification-system.git`
2. Open the project in IntelliJ IDEA (or any Java IDE supporting Maven).
3. Set your query in `Main.java` and run the application.

For detailed architecture, design tradeoffs, and scaling analysis, see [WRITEUP.md](WRITEUP.md).
