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


## Phase 2 — VTX Glass Clock

Phase 2 adds the first real Android home-screen widget:

- VTX Liquid Glass digital clock with Silver/White glass styling.
- Device-local 12-hour or 24-hour time format.
- Localized day and date.
- Resizable home-screen widget.
- Uses Android `TextClock` for live time display rather than a continuously running service.
- Refreshes widget views when system time, date, or time zone changes.

### Add the clock to your home screen

1. Install the Phase 2 APK.
2. Long-press an empty area of the Android home screen.
3. Open **Widgets**.
4. Find **VTX Widget OS** or **VTX Glass Clock**.
5. Drag the clock widget onto the home screen and resize it if supported by the launcher.

Note: actual glass blur depends on the launcher and Android widget rendering. The widget uses translucent layered gradients, a bright glass edge, and soft cool-grey tones to create the Liquid Glass appearance without relying on unsupported live blur.
