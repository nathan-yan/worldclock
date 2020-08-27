# 🌎⏰
A small android widget that tracks what location is at a specific time, instead of what time a specific location is.

## Pictures!
<img src = "https://github.com/nathan-yan/worldclock/blob/master/screenshots/all.png" />

</div>

## How does it work?
I made this a while ago after learning about some astronomy. To make this widget I recorded the unix timestamp, Right Ascension of the sun, the Local Sidereal Time and the longitude I lived at for a given instance in time. 

The Local Sidereal Time (LST) tells which Right Ascension marker is at the local meridian for my longitude, so I can calculate where the sun is relative to my location by subtracting the LST from the Right Ascension of the sun. This number is the number of degrees east the sun is from my location. The longitude of the sun is then my recorded longitude plus the degree offset of the sun. 

Since on average a solar day is (pretty much exactly) 24 hours, I know that the sun moves across the sky at about `360 / (24 * 3600)` degrees per second. With that I just take the current unix timestamp to calculate the seconds elapsed from the recorded data; the sun has since moved `360 / (24 * 3600) * time_elapsed` degrees, and now I know the longitude of the sun at any future time (for at least the next few years). After accounting for an offset to show different times and a very rough (mostly untested) correction for the equation of time, you have the worldclock!

## Customizing the app
Note: unfortunately there is no actual Play Store app for this widget, so you'll need Android Studio to build and run this app on your phone. 
 
There are basically only 2 relevant files for the app: `FixedClockWidget.java` and `WorldClock.java`. To customize the app you only have to edit `FixedClockWidget.java`. 

If you would like to edit the text-time the widget shows ("It's half-past five in", "The sun is overhead in", etc.) change the `time_text` string at the top of the file; you will also have to change the actual numerical offset, represented by the `offset` variable also at the top of the file. This offset represents the number of seconds after noon you want the time to represent. 

As an example, if you want to set your clock to display the location where it is 10 AM, you would set the `time_text` to `"It's <span style = 'color: #ffffff'>ten in the morning</span> in"` and set `offset` to `-2 * 3600`.
