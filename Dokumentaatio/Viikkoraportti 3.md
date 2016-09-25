# Viikkoraporti 3

### Tehty tällä viikolla
- Käyttöliittymän päivityksiä
- Javadoc:ia
- Aloitettu omien tietorakenteiden tekeminen
- Alustavaa testailua algoritmien nopeudesta omilla vs javan valmiilla tietorakenteilla.
- Luokkien ja metodien siistimistä ja pieniä optimointeja.
- Käytetty aika noin 15 tuntia.

## Ohjelman edistyminen

Ohjelma on edistynyt tällä viikolla ehkä vähän vähemmän kuin edellisellä, mutta silti ollaan hyvin aikataulussa. Käyttöliittymä on nyt paljon käyttäjäystäväisempi. Generaatio- ja ratkaisualgoritmien viemä aika ja labyrntin koko esitetään käyttöliittymässä labyrintin kuvan vieressä ja uuden labyrintin voi luoda kayttöliittymästä käsin. Tietorakenteista on tehtynä linkitetty lista ja jono. Vielä olisi tehtävänä ainakin hakupuu toteutettuna todennäköisesti avl-puuna ja prioriteettijono jollain keolla (pairing heap?). Lisäksi jos aikaa riittää niin uusi labyrintin generointialgoritmi tarvitsisi disjoint-set tietorakennetta, jonka toteuttaisin parent pointer treellä.

Opin tällä viikolla toteuttamaan linkitetyn listan ja jonon javalla. Lisäksi luin paljon teoriaa tira materiaaleista ja wikipediasta käsittäen avl- ja red-black- hakupuita, kekoja ja disjoint-set:iä. 

Vaikeuksia ei juurikaan ollut kunhan tajusin, miten tietorakenteita kannattaa lähteä toteuttamaan. Enimmäkseen epäselvyyttä on ohjelman testien kattavuudessa. Käyttöliittymää tuskin tarvitsee testata, joten jäljelle jää tietorakenteet ja algoritmit, joille testejä kirjointakin. Rivi- ja mutaatiokattavuutta en tule saamaan 100% millään. Onko niistä säädetty mitään tarvittavia rajoja?

Seuraavaksi olisi tarkoitus toteuttaa loput tietorakenteet tämänhetkisille algoritmeille. Tämän jälkeen todennäköisesti ajattelin toteutaa käyttöliittymään jonkinlaisen tiedonkeräys toiminnon, joka luo ja ratkaisee x määrän samankokoisia labyrintteja ja tallentaa kuluneet ajat taulukkoon, jossa tiedoille voi tehdä kaikenlaista kuten laskea keskiarvoja, järjestää dataa eri arvojen perusteella ja mielellään tallentaa saatu data tekstitiedostoon. Jos tämä toteutuu nopeasti, niin sitten alan toteuttamaan uutta labyrintin generointialgoritmia ja sen tarvitsemia tietorakenteita. Javadoc:in ja testien parantelua pitää kanssa tehdä vielä ennen projektin loppua.

