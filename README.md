# Runique 🏃

A **multi-module running tracker app** for Android phones and Wear OS devices, built with modern
Android development practices.

---

## 📱 Features

- Track runs with real-time GPS location
- Display route on Google Maps during and after a run
- Record run metrics: distance, duration, pace, and more
- Authentication (register & login)
- Persist run history in a local database
- Background location tracking using WorkManager
- Modular architecture for scalability and maintainability

---

## 🏗️ Architecture & Modules

The project follows a **Clean Architecture** approach, split into feature and core modules:

```
Runique/
├── app/                        # Application entry point, navigation
├── auth/
│   ├── data/                   # Auth network & repository implementations
│   ├── domain/                 # Auth use cases & domain models
│   └── presentation/           # Login & register UI (Compose)
├── core/
│   ├── data/                   # Networking (Ktor), shared data utilities
│   ├── database/               # Room database setup
│   ├── domain/                 # Shared domain models, result types, util
│   └── presentation/
│       ├── designsystem/       # Shared Compose design system (theme, components)
│       └── ui/                 # Shared UI utilities
└── run/
    ├── data/                   # Run repository implementations
    ├── domain/                 # Run use cases & domain models
    ├── location/               # GPS location service
    ├── network/                # Run network datasource
    └── presentation/           # Run tracking & history UI (Compose)
```

---

## 🛠️ Tech Stack

| Category        | Library / Tool                          |
|-----------------|-----------------------------------------|
| Language        | Kotlin                                  |
| UI              | Jetpack Compose + Material 3            |
| Navigation      | Jetpack Navigation Compose              |
| Networking      | Ktor Client (CIO)                       |
| Serialization   | Kotlinx Serialization                   |
| DI              | Koin                                    |
| Local DB        | Room                                    |
| Maps            | Google Maps Compose + Maps Utils        |
| Location        | Google Play Services Location           |
| Background Work | WorkManager                             |
| Image Loading   | Coil                                    |
| Logging         | Timber                                  |
| Build Logic     | Convention Plugins (build-logic module) |
| Min SDK         | API 25 (Android 7.1)                    |
| Target SDK      | API 34 (Android 14)                     |

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or newer
- JDK 17+
- A Google Maps API key

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/fredericomeiraa/Runique.git
   cd Runique
   ```

2. **Add your secrets**

   Create (or edit) `local.properties` in the root directory and add your keys:
   ```properties
   MAPS_API_KEY=your_google_maps_api_key_here
   BASE_URL=https://your-backend-url.com
   ```

3. **Build & Run**

   Open the project in Android Studio and run the `app` module on a device or emulator.

---

## 📦 Build Logic

Custom Gradle convention plugins live in `build-logic/convention` and are applied across modules to
keep build scripts DRY:

| Plugin ID                             | Purpose                         |
|---------------------------------------|---------------------------------|
| `runique.android.application`         | Android app module defaults     |
| `runique.android.application.compose` | Adds Compose to app modules     |
| `runique.android.library`             | Android library module defaults |
| `runique.android.library.compose`     | Adds Compose to library modules |
| `runique.android.feature.ui`          | Feature UI module preset        |
| `runique.android.room`                | Room database setup             |
| `runique.jvm.library`                 | Pure JVM/Kotlin module          |
| `runique.jvm.ktor`                    | Ktor networking preset          |

---

## 👤 Author

**Frederico Coder Meira**
📧 fredericomeiraa@gmail.com

---

## 📄 License

This project is for educational and personal use.

