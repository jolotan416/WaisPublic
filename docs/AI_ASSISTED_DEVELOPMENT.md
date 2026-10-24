# How Wais was built: my work and AI assistance

I used AI coding tools while building Wais, and I want to be open about how. This document explains what I
built myself, how I used **Claude Code** (Anthropic's AI coding agent), and the git-history numbers behind
both. Back to the [README](../README.md).

## Summary

- **I designed and built the foundation alone.** From October 2025 to February 2026 I made 74 commits without
  any AI assistance. They cover the architecture, module structure, infrastructure, design system and the
  first working screens.
- **I brought in Claude Code in March 2026,** after the infrastructure and design philosophy were stable.
- **I stayed in charge of the work.** I set the scope and product direction, steered every design decision,
  reviewed every diff before committing, and opened and merged every pull request.
- **I set guardrails.** I wrote project-specific Claude skills that encode my architecture and UI conventions,
  so generated code follows them.
- **About 45% of non-merge commits** (142 of 313) carry a `Co-Authored-By: Claude` trailer.

## What I built before using AI (Oct 2025 – Feb 2026)

None of the commits from this period involved AI. They established:

- **Project setup and infrastructure:** the Kotlin Multiplatform project, Koin dependency injection, the Room
  database, and the split into `core` / `data` / `domain` / `feature` modules.
- **Architecture and conventions:** Clean Architecture with MVVM and unidirectional data flow, the
  domain layer, the module boundaries and dependency rules, and the `GenericResult` error model.
- **Design system:** the custom Material 3 theme and app-specific theme layer.
- **Navigation:** the Navigation 3 skeleton and bottom navigation.
- **Core features:** the currency selector, the account list with edit mode, account and group forms, the
  dashboard and transaction form, transaction categories and date picker, the currency-conversion API
  integration, and an animated pie chart.
- **Build:** the migration to AGP 9, including splitting out the dedicated `androidApp` module.

## How I used Claude Code (March 2026 onward)

I used Claude Code as a pair programmer that works inside rules I had already set.

| Responsibility | Who |
|---|---|
| Product vision, feature scope, priorities | Me |
| Architecture, design decisions, conventions | Me. Claude proposed approaches, and I chose and refined them. |
| Implementation of larger multi-layer features | Claude, following my prompts and specs, with my review |
| Code review: every diff read, edited or rejected before commit | Me |
| Pull requests: branching, opening, merging | Me |
| Feature specs and technical docs | Drafted by Claude, then iterated with me |
| Automated tests | Mostly written by Claude (18 of the 19 test classes came in Claude-co-authored commits) |

Features where Claude did much of the implementation under my direction:

- savings-goal allocation (the max-flow algorithm)
- recurring transactions
- multiple profiles and transfers between profiles
- manual exchange rates and the move to the Frankfurter API
- scoping form ViewModels to their navigation entries
- opt-in analytics consent and the privacy policy
- release signing and beta-release preparation

### Guardrails: project Claude skills

I turned my conventions into six **Claude skills**, which are step-by-step instructions the agent loads before
working on that part of the codebase. Generated code then follows my standards instead of generic defaults.

| Skill | What it enforces |
|---|---|
| Presentation layer | ViewModel and screen structure, list and form patterns, validated field state, DI registration |
| Data and domain layer | Room entities and DAOs, repositories, DataStore and Ktor APIs, use cases returning `GenericResult` |
| Module architecture | How to add a new `core`/`data`/`feature` module and wire it in |
| Koin dependency injection | Which DSL to use for each layer, parameters, platform bindings, common pitfalls |
| Compose navigation | The custom Navigation 3 backstack, routes, and selector-returns-a-value pattern |
| Compose UI rules | No hard-coded strings or dimensions, always the app theme instead of `MaterialTheme`, reuse of shared components, the Styles API |

### What I kept doing by hand after March 2026

AI didn't replace hands-on work. These were done without AI assistance:

- **UI migration to the Compose Styles API** (for example, a dashboard UI refactor PR with 11 commits, none
  AI-assisted)
- **Onboarding flow** (about 15 commits)
- **Firebase Analytics and Crashlytics integration** on Android and iOS
- **Visual polish:** UI fixes, the pastel color palette, the app icon and name
- **Maintenance and releases:** dependency, Gradle and AGP upgrades, versioning, changelogs, iOS release prep

## By the numbers

All figures come from the private repository's git history (October 2025 – October 2026).

| Metric | Value |
|---|---|
| Total commits | 342 (313 regular commits + 29 merges) |
| Commits with a Claude co-author trailer | 142 of 313 (45%) |
| Commits before first AI use | 74, all without AI |
| First AI-assisted commit | 2026-03-01 |
| Claude models used | Claude Opus 4.6, 4.7, 4.8, 5 and 5.5; Claude Sonnet 4.6 and 5 |
| Lines added, Claude-co-authored commits | ~40,900 (about 34,500 Kotlin, including ~3,100 test lines) |
| Lines added, other commits | ~19,100 (about 15,500 Kotlin) |

AI involvement over time (non-merge commits):

| Period | Total | With Claude trailer |
|---|---|---|
| Oct 2025 – Feb 2026 | 73 | 0 |
| Mar – May 2026 | 58 | 33 |
| Jun – Aug 2026 | 92 | 50 |
| Sep – Oct 2026 | 90 | 59 |

AI-assisted commits have grown as the codebase matured. The more conventions and skills were in place, the
more implementation work I could safely delegate.

## Limits of these numbers

- A co-author trailer means Claude **contributed** to a commit, not that it wrote all of it. Most of those
  commits include my own edits and review.
- Line counts measure volume, not difficulty, and they don't capture design decisions.
- Commits without a trailer are my own work as far as the record shows.

## This repository

The documentation in this public repository (README and `docs/`) was drafted with Claude Code from the private
repository and its git history. I then reviewed and corrected it.
