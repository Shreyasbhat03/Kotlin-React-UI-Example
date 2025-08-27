/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/kotlin/**/*.kt",
    "./src/main/resources/**/*.html"
  ],
  theme: {
    extend: {
      fontFamily: {
        'sans': ['-apple-system', 'BlinkMacSystemFont', 'Segoe UI', 'Roboto', 'sans-serif'],
      }
    },
  },
  plugins: [],
}