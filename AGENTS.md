# Devin Task Instructions

You are working in the repository root of this project.

## Goal

Perform a full framework/runtime modernization of this application from Java 8 + Spring Boot 2.7.x to Java 25 + Spring Boot 4.x, then push the changes, open a PR to `main`, and document the migration.

## Context

- Current project is a Gradle-based Spring Boot app.
- Upgrade target is Java 25 and Spring Boot 4.x.
- As of June 21, 2026, Java 25 is GA and Spring Boot 4.x is available.
- Prefer the latest stable 4.x release compatible with the repo at the time you work, unless that creates avoidable breakage. If you choose a specific patch version, document why.

## Execution Requirements

1. Create a new branch from the current default branch.
   - Branch name format: `chore/java25-springboot4-migration`

2. Analyze the current codebase and migrate everything required for :
   - Java 25
   - Spring Boot 4.x
   - related Spring dependency ecosystem changes
   - Jakarta namespace migrations where required
   - Gradle/plugin updates where required
   - test/build/runtime compatibility

3. Do the migration safely and incrementally.
   - If needed, use a staged path internally, for example:
   - Spring Boot 2.7.x -> 3.x
   - then Spring Boot 3.x -> 4.x
   - But the final branch state must be on Java 25 + Spring Boot 4.x.

4. Update all affected code and configuration, including but not limited to:
   - `build.gradle`
   - Gradle wrapper if needed
   - Java source/target/toolchain settings
   - deprecated/removed APIs
   - `javax.*` to `jakarta.*` migrations where required
   - Spring configuration and annotations
   - JAXB / validation / annotation dependencies
   - H2 or other dependency/version compatibility
   - any legacy startup/runtime issues exposed by newer JDKs

5. Preserve application behavior as much as practical.
   - Existing endpoints should continue working unless a change is unavoidable.
   - If behavior must change, document it clearly.

6. Build and verify the project.
   - Run a clean build.
   - Run tests.
   - Start the application successfully.
   - Verify the app boots without migration-related errors.
   - Verify key endpoints if possible.

7. If you hit migration blockers:
   - Resolve them directly if feasible.
   - Do not stop at partial edits.
   - Only leave TODOs if they are genuinely unavoidable, and document them clearly.

8. Create a Markdown migration report in the repo root named:
   - `JAVA25_SPRINGBOOT4_MIGRATION_REPORT.md`

9. That report must include:
   - summary of what changed
   - old versions and new versions
   - major code migrations performed
   - dependency changes
   - breaking changes or behavior changes
   - build/test/run results
   - commands used to verify
   - any remaining risks or follow-ups

10. Commit the changes with a clear commit message, push the branch to remote, and create a PR to `main`.

11. PR requirements:
   - Title: `Migrate project to Java 25 and Spring Boot 4`
   - Include a concise but complete PR description
   - Mention that team lead review/approval is required before merge
   - Do not merge the PR yourself

12. Final deliverables:
   - pushed branch
   - open PR to `main`
   - successful build
   - app starts successfully
   - migration report file committed

## Acceptance Criteria

- Project builds successfully
- Tests pass, or any failing tests are clearly explained and fixed if possible
- App runs successfully on Java 25
- Project is on Spring Boot 4.x
- Required namespace/dependency/config migrations are complete
- PR is open against `main`
- `JAVA25_SPRINGBOOT4_MIGRATION_REPORT.md` exists and is committed

## Output Expectation

At the end, provide:

- branch name
- exact Java version used
- exact Spring Boot version used
- build/test/run status
- PR URL
- short summary of major migration changes
