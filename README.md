# Fantasy Formula 1

## It's Lights Out and Away You Go...
### ...With Your Own F1 Team!  
##

Looking to start a Fantasy F1 Championship amongst family, friends, or coworkers? 

Try Fantasy Formula 1:

- Choose *3 drivers each* to drive for your team
- Follow along for **weekly races** at iconic tracks like Monaco and Silverstone
- Compete for the ultimate prize, the ***Fantasy Constructor's Championship!***

This application will allow an *unlimited* amount of users to compete in a Fantasy Formula 1 league 
using their own custom-built teams. Examples of users could include family members, friends who follow
F1, or coworkers looking to host a friendly competition. 

Each team is constructed of *three drivers*, and points will be awarded according to the finishing placement 
of each driver at the end of each week's Grand Prix. The team with the most points at the conclusion of the season 
is crowned the champion, and any ties are resolved by selecting the team with the most wins overall; if another tie 
results, the champion is the team with the greater total number of fastest laps.

As a relatively new fan of Formula 1, this project will allow me to connect with others who enjoy watching
weekend races and engaging in a fun contest that spans the F1 season. Moreover, running any Fantasy league is 
a multi-faceted process and provides many opportunities for me to demonstrate my knowledge of software construction. 

## User Stories

- As a user, I want to be able to view a scoreboard summarizing all the teams in my league and their respective points
and wins
- As a user, I want to be able to select a previous race and view its results
- As a user, I want to be able to add a race with the top ten drivers' final placements and have every team's
  points, wins, and fastest laps be updated accordingly
- As a user, I want to be able to select a team and view a list of drivers on that team and its total points and wins
- As a user, I want to be able to select a team and add a new driver to the team
- As a user, I want to be able to select a team and remove a driver from the team
- As a user, I want to be able to select a team and remove it from my league
- As a user, I want to be able to create a team and add it to my league
- As a user, I want to be able to select a driver and view their name, number, points, wins, and fastest laps
- As a user, I want to be able to select a driver and add to their points total
- As a user, I want to be able to select a driver and subtract from their points total
- As a user, I want to be able to select a driver and add a win
- As a user, I want to be able to select a driver and add a fastest lap
- As a user, I want to be able to select a driver and change their driver number
- As a user, I want to be able to declare the league winner with the most points, or the most wins if there is a tie,
or the greater number of fastest laps if there is another tie
- As a user, I want to be able to save the current state of the Fantasy F1 League, including all races, teams, 
and drivers
- As a user, when I start the Fantasy F1 application, I want to be able to reload my Fantasy F1 League from file

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking the "Teams" button on the main menu
which redirects you to the Teams page, where you can then click the "Add Team" button and go through the displayed
instructions to enter information related to your new team; once completed, this new team will be displayed in the
Teams page
- You can generate the second required event related to adding Xs to a Y by clicking the "Teams" button on the main menu
which redirects you to the Teams page, where you can then click the "Remove Team" button and select an existing team
that you would like to remove; after, that selected team will be removed from the Teams page where it was previously
displayed
- You can locate my visual component by clicking the "Teams" button on the main menu which redirects you to the Teams
page, where you can then select any team to view their page and click the "Show Team Performance Graph" button which
will display a graph showing the points scored by each driver for that team
- You can save the state of my application by clicking the "Save League" button on the main menu
- You can reload the state of my application by clicking the "Load League" button on the main menu

# Image Credits

drivers.png: https://www.motorsportweek.com/wp-content/uploads/2020/07/jm2004jy181.jpg

load.png: https://www.sportscasting.com/wp-content/uploads/2021/06/Race-winner-Lewis-Hamilton-Mercedes-GP.jpg

logo.png: https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/F1.svg/2560px-F1.svg.png

races.png: https://e00-marca.uecdn.es/assets/multimedia/imagenes/2022/09/08/16626643646718.jpg

save.png: https://static.autox.com/uploads/2018/09/f1-2018-kimi-raikkonen-ferrari-leave.jpg

scoreboard.png: https://www.formula1.com/content/dam/fom-website/manual/Misc/2021preseason/GettyImages-1264859189.jpg.transform/9col/image.jpg

teams.png: https://www.formula1.com/content/dam/fom-website/manual/Misc/2022manual/2022SummerBreak/h2h/JPG-RGB-72-DPI-2022_Supershot_V_Formation_Final.jpg

trophy.jpg: http://www.americanmetalartsstudios.com/images/awards/images/F1_drivers_trophy.jpg

winner.png: https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSM0vF2UZfk-ykG9NPoUyO9z7NJ97DmQQyJXWqRezLAluBi01kUubvYqtjZQo9lrW7G3Eo&usqp=CAU

# Phase 4: Task 2

Sun Nov 20 19:00:07 PST 2022

Brazilian Sprint Race added to My Fantasy F1 League.

Sun Nov 20 19:00:17 PST 2022

Mina's Team added to My Fantasy F1 League.

Sun Nov 20 19:00:22 PST 2022

Mina's Team added to Fernando Alonso's list of teams.

Sun Nov 20 19:00:22 PST 2022

Fernando Alonso added to Mina's Team.

Sun Nov 20 19:00:28 PST 2022

Mina's Team added to Kevin Magnussen's list of teams.

Sun Nov 20 19:00:28 PST 2022

Kevin Magnussen added to Mina's Team.

Sun Nov 20 19:01:04 PST 2022

Brazilian Grand Prix added to My Fantasy F1 League.

Sun Nov 20 19:01:13 PST 2022

Michael's Team removed from My Fantasy F1 League.

Sun Nov 20 19:01:20 PST 2022

Mina's Team removed from Fernando Alonso's list of teams.

Sun Nov 20 19:01:20 PST 2022

Fernando Alonso removed from Mina's Team.

Process finished with exit code 0

# Phase 4: Task 3

- Overall, I feel satisfied that the classes in the model package maximize cohesion and reduce coupling;
although there is a bidirectional relationship between the Driver and Team classes, the utility of the 
Fantasy F1 league requires that they 'know' about one another.

- Since the structure of the persistence package is quite simple as well, it is clearly following the 
single responsibility principle and has only moderate coupling which cannot be minimized further.

- I could increase cohesion in the ui package by refactoring the FantasyGUI class into separate classes.
More specifically, I see that there are two types of associations that the FantasyGUI class has, firstly
to the model package and secondly to the persistence package; it could be split into separate classes
so that these types of associations are separated. Looking further into the class as it is currently 
constructed, I would also create separate classes for the different functionalities that the graphic user
interface offers: one for the Drivers interface, one for the Teams interface, etc.

- Likewise, there is a similar pattern of associations in the FantasyApp class that I would tackle by
also refactoring it into separate classes. 