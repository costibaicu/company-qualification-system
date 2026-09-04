# Description: Intelligent Company Qualification and Classification System

## System Approach and Architecture

The system bridges the gap between noisy search and expensive Large Language Model (LLM) calls using a lightweight hybrid scoring and heuristic engine built with Java and Jackson.

### Components:

- **DataLoader:** Parses `companies.jsonl` data line by line using robust JSON tree parsing (`JsonNode`), safely handling inconsistent schemas without deadlocks.

- **IntentEngine:** Parses the user-entered query, extracts main keywords, and applies hard structural filters (e.g., checking the `is_public` state).

- **Scoring System:** Calculates a relevance score for each candidate company based on keyword occurrences in key fields (`operational_name`, `address`, `core_offerings`, `target_markets`, and `description`).

- **Ranking and Sorting:** Filters out candidates with zero scores and sorts the remaining matches in descending order of their relevance score.

### Why this design?

It avoids the high financial cost and latency of calling an LLM for each candidate company, while providing significantly higher accuracy and contextual intent matching than simple cosine vector similarity.

## Trade-offs

We optimized for Speed, Cost-Effectiveness, and Simplicity, making the following trade-offs:

- We opted for keyword-weighted scoring and structural rule checking instead of heavy natural language embedding models. This ensures execution completes in milliseconds.

- Strict filters (such as checking for the word “public”) prioritize accuracy, ensuring that incorrect candidate entries are eliminated early.

## Error Analysis

**Where does the system struggle?**

- **Synonym Mismatch:** If a query specifies “freight transportation,” but a company description uses terms with no overlapping keywords, the scoring system may underestimate or miss the match.

- **Geographic inference:** Complex regional nuances (e.g., requesting companies in the “DACH region”) require explicit country mapping via dictionary, which a purely keyword-based approach might not fully address.

## Scaling (Handling Over 100,000 Companies)

If the dataset were to scale from hundreds to over 100,000 entries per query, the following changes would be implemented:

- **Indexing:** Introducing an inverted index for fast text search instead of linear stream scanning over raw lists.

- **Caching and pre-computation:** Pre-tokenizing and pre-processing of offers and company descriptions.

- **Parallel processing:** Using Java parallel streams (`parallelStream()`) or distributed execution frameworks to distribute the scoring load across multiple CPU cores.

## Failure Modes

**When might the system produce reliable but incorrect results?**

- If a company profile lists popular keywords or industries in its description to bid for visibility, the scoring system could give it an inflated rank despite a lack of core competency.

- To detect these failures, production monitoring should track click-through rates, user feedback on top results, and automated semantic validation samples using lightweight LLM spot checks on borderline scores.
