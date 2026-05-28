# Numerosity Website

Numerosity is a math practice platform built around a Vaadin + Spring Boot application, Firebase-backed account support, and a public REST API that other developers can integrate into their own websites.

This folder contains the public-facing static site for the project.

## What Developers Can Use

- `GET /api/v1/health`
- `GET /api/v1/questions`
- `GET /api/v1/questions/category/{category}`
- `GET /api/v1/questions/difficulty/{difficulty}`
- `GET /api/v1/questions/random`
- `GET /api/v1/users/{userId}`

See [`README.quickstart.md`](README.quickstart.md) for a fast setup guide.
See [`README.dev.md`](README.dev.md) for the full integration guide.

## Public Site

The landing page now includes:

- a download button for the developer README
- a summary of the public `/api/v1` endpoints
- a concise explanation of browser-based API integration

## Running the Site Locally

Serve the `server/website/` directory with any static web server and open `index.html`.

The README viewer works best when the markdown files are served from the same directory.

## Notes

- The website is documentation and promotion for the live app.
- The app and API live in the `server/` project.
- If you are integrating Numerosity into another product, start with `README.dev.md`.
