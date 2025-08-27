# Kotlin React Tailwind Project

This project is a Kotlin/JS React setup with Tailwind CSS v3 for styling.

## Installation / Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Shreyasbhat03/Kotlin-React-UI-Example
````

### 2. Create Project in IntelliJ

1. Open IntelliJ IDEA.
2. File → New → Project from Existing Sources → select cloned folder.
3. Make sure Gradle and Kotlin/JS are selected.
4. Finish and wait for indexing.

### 3. Install Tailwind CSS CLI v3

```bash
npm install -D tailwindcss@3 postcss autoprefixer
```

* `tailwindcss@3` → specifically installs Tailwind v3
* `postcss` → required for processing Tailwind
* `autoprefixer` → ensures CSS works across browsers

### 4. Initialize Tailwind Config

```bash
npx tailwindcss init -p
```

This generates both `tailwind.config.js` and `postcss.config.js` in the project.

### 5. Verify Tailwind Installation

```bash
npx tailwindcss -v
```

Expected output:

```
Tailwind CSS v3.x.x
```

---

## Tailwind Configuration

Update `tailwind.config.js` in the project:

```javascript
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/kotlin/**/*kt",
    "./src/main/resources/**/*.html"
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
```

---

## Project Structure

Create inside `src/main/resources`:

```
resources/
├─ styles/
│  └─ tailwind.css
├─ css/
└─ index.html
```

### tailwind.css

```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

### index.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Title Name</title> 
    <link rel="stylesheet" href="css/output.css">
</head>
<body>
<div id="root"></div>
<script src="ProjectName.js"></script> 
</body>
</html>
```

---

## Kotlin Files

### App.kt

Create `App.kt` inside your Kotlin folder and define your UI and styles.

### main.kt

```kotlin
import react.create
import react.dom.client.createRoot
import kotlinx.browser.document
import web.dom.Element

fun main(){
    val container = document.getElementById("root") as Element
    val root = createRoot(container)
    root.render(App.create())
}
```

---

## build.gradle.kts

```kotlin
val wrappersVersion = "pre.758"

plugins {
    kotlin("js") version "1.9.23"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.jetbrains.space/public/p/kotlin-wrappers/maven")
    }
}

dependencies {
    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:1.0.0-pre.758"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled = true
                }
            }
        }
    }
}
```

---

## Generate Tailwind CSS

```bash
npx tailwindcss -i ./src/main/resources/styles/tailwind.css -o ./src/main/resources/css/output.css
```

---

## Run the Application

```bash
./gradlew browserDevelopmentRun
```

---

## If You Cloned an Existing Project

```bash
npx tailwindcss -i ./src/main/resources/styles/tailwind.css -o ./src/main/resources/css/output.css
./gradlew browserDevelopmentRun
```

---

## Contributing

Pull requests are welcome. For major changes, please open an issue first.

Please make sure to update tests as appropriate.

---


