# VTX Widget OS — Phase 1

Light Liquid Glass / Silver + White design foundation for Android 16.

## Requirements
- Android Studio with Android SDK Platform 36 installed
- JDK 17
- Gradle 8.11.1 (or use the GitHub Actions workflow)
- Internet access for Gradle dependency resolution

## Build
Open this folder in Android Studio, allow Gradle sync, then run:

```bash
gradle :app:assembleDebug
```

APK output:
`app/build/outputs/apk/debug/app-debug.apk`

## GitHub Actions
Push this project to GitHub. The workflow at `.github/workflows/android.yml` builds a debug APK on push to `main`, pull requests, or manual dispatch.

## Phase 1 scope
Included:
- Compose app shell and five-tab navigation
- Light Liquid Glass / Silver + White design tokens
- Home, Discover, Create preview, My Widgets empty state, Settings
- Room database entity, DAO, database singleton and repository foundation
- Android API 36 target, minimum API 29

Not yet implemented:
- Actual home-screen AppWidget providers
- Persistent visual builder
- Live widget renderer and placement flow
- Weather provider / network service
- Full settings persistence

Those belong to subsequent development phases. The Phase 1 Create page is intentionally a preview, not a falsely advertised functional builder.

## Design note
Android AppWidgets have host-imposed sizing and rendering limits. A Liquid Glass appearance is approximated with translucent surfaces, subtle borders, gradients and shadows; true system-wide backdrop blur is not guaranteed on all launchers.
