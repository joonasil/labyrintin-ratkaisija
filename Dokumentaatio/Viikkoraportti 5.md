#Viikkoraportti 5
###Tehty tällä viikolla
  * ArrayList ja DisjointSet tietorakenteet
  * Kaikille käytössä oleville tietorakenteille JUnit testejä
  * Labyrintin generointialgoritmit DFS ja Kruskal
  * Labyrintin ratkaisualgoritmi IDA*
  * Vanhat generointialgoritmit kirjoitettu uudestaan tehokkaammin
  * Luokille tehty lisää dokumentaatiota
  * Käyttöliittymään lisätty labyrintin zoomaus ja tallennusominaisuudet
  * Toteutus- ja testausdokumentaatiota tehty
  * Ajettava .jar tiedosto generoitu
  
###Ohjelman edistyminen
Ohjelma alkaa olemaan toiminnaltaan valmis. Paljon on tullut tehtyä kahden viikon aikana ja olen tyytyväinen sovellukseni ulkonäköön ja toimintaan. Luokkia voi vielä siistiä ja ehkä jopa jakaa jokainen generointi- ja ratkaisualgoritmi omaksi luokakseen, jos koen sen tarpeelliseksi ohjelman selvyyden kannalta. Käyttöliittymän luokkia voin kanssa tarpeen vaatiessa pätkiä pienempiin paloihin. Tein kanssa huvikseen dokumentaatiota varten gif animaatioita algoritmien toiminnasta.
  
Tällä viikolla uutena asiana tullut DisjointSet ja sen toteutus. Kyseisen tietorakenteen pitäisi toimia lähes vakioajassa, mutta silti labyrintin generointi kruskalin algoritmilla, joka käyttää disjointSet:tiä vie huomattavasti muita generointialgoritmeja enemmän aikaa.
  
Ongelmia ei tällä hetkellä ainakaan tule mieleen. Nyt olisi tarkoitus tehdä lisää dokumentaatiota, suorituskykytestausta ja siistiä luokkia.
