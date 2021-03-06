/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
 
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.time.chrono.IsoChronology;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.Period;

public class Person {
  
    public enum Sex {
        MALE, FEMALE
    }
  
    String name; 
    LocalDate birthday;
    Sex gender;
    String emailAddress;
  
    Person(String nameArg, LocalDate birthdayArg,
        Sex genderArg, String emailArg) {
        name = nameArg;
        birthday = birthdayArg;
        gender = genderArg;
        emailAddress = emailArg;
    }  

    public int getAge() {
        return birthday
            .until(IsoChronology.INSTANCE.dateNow())
            .getYears();
    }

    public void printPerson() {
      System.out.println(this);
    }
    
    public Sex getGender() {
        return gender;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public LocalDate getBirthday() {
        return birthday;
    }
    
    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public static List<Person> createRoster() {
        
        List<Person> roster = new ArrayList<>();
        roster.add(
            new Person(
            "Fred",
            IsoChronology.INSTANCE.date(1980, 6, 20),
            Person.Sex.MALE,
            "fred@example.com"));
        roster.add(
            new Person(
            "Jane",
            IsoChronology.INSTANCE.date(1990, 7, 15),
            Person.Sex.FEMALE, "jane@example.com"));
        roster.add(
            new Person(
            "George",
            IsoChronology.INSTANCE.date(1991, 8, 13),
            Person.Sex.MALE, "george@example.com"));
        roster.add(
            new Person(
            "Bob",
            IsoChronology.INSTANCE.date(2000, 9, 12),
            Person.Sex.MALE, "bob@example.com"));
        
        return roster;
    }

    public static List<Album> createAlbums() {
        
        final int MAX_RATING = 5;

        List<Album> albums = new ArrayList<>();
        
        for (int i = 0; i < MAX_RATING; i++) {
            Track[] tracks = new Track[MAX_RATING - i];
            
            for (int j = 0; j < tracks.length; j++) {
                tracks[j] = new Track(i % 2 == 0 ? j : MAX_RATING - j);
            }

            albums.add(new Album("Album " + (char)('Z' - i), tracks));
        }
        
        return albums;
    }

    public String toString() {
        return name + ", " + this.getAge();
    }

    public static void main(String[] args) {
        List<Person> roster = createRoster();
        
        System.out.println("");
        System.out.println("Printing the whole roster: " + roster);
        
        System.out.println("");
        System.out.println("Printing males using 'enhanced' for loop:");
        
        for (Person p : roster) {
            if (p.getGender() == Person.Sex.MALE) {
                System.out.println(p.getName());
            }
        }
        
        System.out.println("");
        System.out.println("Printing males using a pipeline:");
        
        roster
            .stream()
            .filter(p -> p.getGender() == Person.Sex.MALE)
            .forEach(Person::printPerson);

        List<Album> albums = createAlbums();
        
        System.out.println("");
        System.out.println("Printing the whole list of albums:");
        System.out.println(albums);

        List<Album> favs = new ArrayList<>();
        for (Album a : albums) {
            boolean hasFavorite = false;
            for (Track t : a.tracks) {
                if (t.rating >= 4) {
                    hasFavorite = true;
                    break;
                }
            }
            if (hasFavorite)
                favs.add(a);
        }
        Collections.sort(favs, new Comparator<Album>() {
                                public int compare(Album a1, Album a2) {
                                    return a1.name.compareTo(a2.name);
                                }});
        
        System.out.println("");
        System.out.println("Printing favourite albums, obtained from nested for loops:");
        System.out.println(favs);

        List<Album> favourites = albums
            .stream()
            .filter(a -> a.tracks
                .stream()
                .anyMatch(t -> t.rating >= 4))
            .sorted(Comparator.comparing(a -> a.name))
            .collect(Collectors.toList());
        
        System.out.println("");
        System.out.println("Printing favourite albums, obtained from lambda expressions and aggregate operations:");
        System.out.println(favourites);
    }
    
}
