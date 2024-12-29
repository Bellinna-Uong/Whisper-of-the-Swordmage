package game.place;

import game.objects.item.Bed;
import game.objects.item.Crystal;
import game.objects.item.Sword;
import game.objects.monster.Harpy;
import game.objects.monster.Orc;
import game.objects.monster.Medusa;


import java.util.HashMap;
import java.util.Map;

public class DungeonBuild {
    private Map<String, Room> rooms;

    public DungeonBuild() {
        rooms = new HashMap<>();
    }

    public Map<String, Room> createDungeon() {
        Harpy harpy = new Harpy();
        Orc orc = new Orc();
        Medusa medusa = new Medusa();
        Sword sword = new Sword();
        Crystal crystal = new Crystal();
        Bed bed = new Bed();


        // Create rooms
        Room start = new Room("Starting point","");
        //Room one = new Room("Room 1","");
        Room two = new Room("Room 2","");
        Room three = new Room("Room 3","");
        //Room four = new Room("Room 4","");
        Room five = new Room("Room 5","");
        Room six = new Room("Room 6","");
        Room seven = new Room("Room 7","");
        //Room eight = new Room("Room 8","");
        Room nine = new Room("Room 9","");
        Room ten = new Room("Room 10","");
        Room eleven = new Room("Room 11","");
        Room twelve = new Room("Room 12","");
        Room thirteen = new Room("Room 13","");
        //Room fourteen = new Room("Room 14","");
        //Room fifteen = new Room("Room 15","");
        //Room sixteen = new Room("Room 16","");
        //Room seventeen = new Room("Room 17","");
        Room eighteen = new Room("Room 18","");
        //Room nineteen = new Room("Room 19","");
        Room twenty = new Room("Room 20","");
        Room boss = new Room("Boss room","");
        Room secret = new Room("Secret room","");

        Corridor corridor4 = new Corridor("A narrow, dimly lit passage.");
        corridor4.addConnection("North",five);
        corridor4.addConnection("East",nine);
        corridor4.addConnection("South",six);
        corridor4.addConnection("West",two);

        rooms.put("Starting point", start);
        rooms.put("Room 2", two);
        rooms.put("Room 3", three);
        rooms.put("Room 5", five);
        rooms.put("Room 6", six);
        rooms.put("Room 7", seven);
        rooms.put("Room 9", nine);
        rooms.put("Room 10", ten);
        rooms.put("Room 11", eleven);
        rooms.put("Room 12", twelve);
        rooms.put("Room 13", thirteen);
        //rooms.put("Room 17", seventeen);
        rooms.put("Room 18", eighteen);
        rooms.put("Room 20", twenty);
        rooms.put("Boss room", boss);
        rooms.put("Secret room", secret);

        start.connect("East", two);
        two.connectCorridor("East", corridor4);
        two.connect("South", three);
        two.connect("West", start);
        three.connect("North", two);
        five.connect("East", twelve);
        five.connectCorridor("South", corridor4);
        six.connectCorridor("North", corridor4);
        six.connect("South", seven);
        seven.connectCorridor("North", corridor4);
        seven.connect("East", ten);
        nine.connectCorridor("West", corridor4);
        ten.connect("South", eleven);
        ten.connect("West", seven);
        twelve.connect("South", thirteen);
        twelve.connect("West", five);
        thirteen.connect("North", twelve);
        thirteen.connect("East",eighteen);
        eighteen.connect("East",twenty);
        eighteen.connect("West",thirteen);
        twenty.connect("East",secret);
        twenty.connect("South",boss);
        twenty.connect("West",eighteen);
        secret.connect("West",eighteen);

        start.addObject("West", bed);
        three.addObject("East", sword);
        three.addObject("South", crystal);
        five.addObject("West", orc);
        seven.addObject("South", orc);
        nine.addObject("North", sword);
        nine.addObject("East", harpy);
        ten.addObject("North", sword);
        twelve.addObject("North", bed);
        twelve.addObject("East", sword);
        thirteen.addObject("West", harpy);
        eighteen.addObject("South", bed);
        twenty.addObject("North", orc);
        boss.addObject("West",medusa);


        return rooms;
    }


}
