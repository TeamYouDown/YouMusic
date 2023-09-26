![MAIN HEADER](https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/app_banner_youmusic.jpg?raw=true)

[![Latest release](https://img.shields.io/github/v/release/TeamYouDown/YouMusic?include_prereleases)](https://github.com/TeamYouDown/YouMusic/releases)
![](https://img.shields.io/badge/Required-Android%206.0%2B-brightgreen.svg)
![](https://img.shields.io/badge/Version-1.0.5-green.svg)
[![License](https://img.shields.io/github/license/TeamYouDown/YouMusic)](https://www.gnu.org/licenses/gpl-3.0)
[![Downloads](https://img.shields.io/github/downloads/TeamYouDown/YouMusic/total)](https://github.com/TeamYouDown/YouMusic/releases)

# YouMusic - Ad-Free and Free YouTube Music/MP3 Download and Listening Application

## Downloadable Platforms

<img src="https://youdown.net/material/img/app-gallery.png">  <img src="https://youdown.net/material/img/get-app.png">

1. [Huawei AppGallery](https://appgallery.huawei.com/app/C108194695)
2. [Xiomi GetApps](https://global.app.mi.com/details?id=com.ozsoft.youmusic)

Make your own music library with any song from YouTube Music.  
No ads, free, and simple.

* ***Still any Doubts / Problems ? (or) Want to suggest me any new Feature / Idea ?***
     * Feel free to contact me any time through any of the following sources.
     * Mail me to : info@youdown.net
     * Join our [Telegram Group](https://t.me/YouDown_Chat)
     * Find other ways from [My Website](https://youdown.net)

> **Note**
>
> **1.** The project is currently in an unstable stage, so there should be many bugs. If you encounter one, please report by opening an issue.
>
> **2.** The icon of this app is temporary. It will be changed in the future.

With this app, you're like getting a free music streaming service. You can listen to music from YouTube Music and build your own library. What's more, songs can be downloaded for offline playback. You can also create playlists to organize your songs. The aim of _YouMusic_ is to enable everyone to listen to music at no cost by an easy-to-use, practical and ad-free application.

> **Warning**
> 
>If you're in region that YouTube Music is not supported, you won't be able to use this app ***unless*** you have proxy or VPN to connect to a YTM supported region.

## Features

### YouTube

- Play songs without ads
- Browse almost any YouTube Music page
- Search songs, albums, videos and playlists from YouTube Music
- Open YouTube Music links

### Library

- Save songs, albums and playlists in local database
- Download music for offline playback
- Like songs
- local playlist management
- Add links to your favorite YouTube Music playlists
- Export downloaded songs via SAF

### Player

- Material design player
- Lockscreen playback
- Cache songs
- (Synchronized) lyrics
- Skip silence
- Audio normalization
- Stat for nerds
- Persistent queue

### Other

- Custom themes
- Dark theme
- Localization
- Proxy
- Backup & restore
- Support Android Auto

## Screenshots

<p float="left">
  <img src="https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/en1.png" width="170" />
  <img src="https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/en2.png" width="170" />
  <img src="https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/en3.png" width="170" />
  <img src="https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/en4.png" width="170" />
</p>
<p float="left">
  <img src="https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/en5.png" width="170" />
  <img src="https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/en6.png" width="170" />
  <img src="https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/en7.png" width="170" />
    <img src="https://github.com/TeamYouDown/YouMusic/blob/main/fastlane/metadata/android/en-US/images/phoneScreenshots/en8.png" width="170" />
</p>

## Installation

You can install _YouMusic_ using the following methods:

1. Download the APK file from [GitHub Releases](https://github.com/TeamYouDown/YouMusic/releases).
2. If you want to have bug fixes or enjoy new features quickly, install debug version from [GitHub Action](https://github.com/TeamYouDown/YouMusic/actions) at your own risk and remember to backup your data more frequently.
3. Clone this repository and build a debug APK.

How to get updates?

1. YouMusic application.
2. [GitHub](https://github.com/TeamYouDown/YouMusic) + [YouMusic](https://youdown.net)

## FAQ

### Q: Is there any working demo?

Yes there is a demo installed on Google Play, AppGallery and GetApps.
1. [Huawei AppGallery](https://appgallery.huawei.com/app/C108194695)
2. [Xiomi GetApps](https://global.app.mi.com/details?id=com.ozsoft.youmusic)

### Q: How to export downloaded song files?

*YouMusic* supports SAF. You can find the provider in Android native file manager. You can also use [instruction](https://github.com/TeamYouDown/YouMusic/issues) (recommended).

### Q: Why YouMusic isn't showing in Android Auto?

1. Go to Android Auto's settings and tap multiple times on the version in the bottom to enable developer settings
2. In the three dots menu at the top-right of the screen, click "Developer settings"
3. Enable "Unknown sources"

## Contribution

### Contributing Translations

#### App

1. Have a fork of this project.
2. If you have Android Studio, right click on the `app/src/main/res/values` folder, select "New"->"Values Resource File". Input `strings.xml` as file name. Select "Locale", click ">>", choose your language and region, and click "OK".
3. If not, create a folder named `values-<language code>-r<region code>` under `app/src/main/res`. Copy `app/src/main/res/values/strings.xml` to the created folder.
4. Replace each English string with the equivalent translation. Note that lines with `translatable="false"` should be ignored.
5. (Recommended) Build the app to see if something is wrong.
6. Make a pull request with your changes. If you do step 5, the process of accepting your PR will be faster.
