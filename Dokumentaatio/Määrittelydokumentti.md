# Määrittekydokumentti

Tarkoituksena olisi toteuttaa labyrintin ratkaiseva sovellus käyttäen ainakin A*- ja Dijkstran algoritmia mahdollisesti myös Bellman-Ford. 
Lisäksi tarkoitus olisi tehdä ainakin yksi algoritmi labyrintin luomista varten. Ideoin käyttäväni seedattua randomisoitua Kruskalin 
algoritmia tai seedattua randomisoitua Primin algoritmia. Ajan niin salliessa saatan myös toteuttaa kummatkin ja vertailla labyrinttien 
mahdollisia eroavaisuuksia, kun algoritmit käyttävät samaa seediä.

Tietorakenteina käytetään A*- algoritmissä prioriteettijonoa, joka on toteutettu pairing kekona tai fibonacci kekona. Samaa prioriteettijona
käytetään Dijkstran algoritmissa. Labyrintin generointiin käytettävä Kruskalin algoritmin toteutan Disjoint-setillä jonka taas toteutan
linked listillä, jossa jokaisella listan nodella on pointteri listan ensimmäiseen nodeen joka on jokaisen erillisen setin "edustaja". 
Varmasti tulen vielä ohjelman toteutuksen aikana etsimään lisää tietoa eri tietorakenteiden ja algoritmien toteuksesta ja hyödyistä
varsinkin koska tirasta on ehtinyt kulua jo kaksi vuotta ja tarvitsee taas muistuttaa mieleen kaikki aika- ja tilavaatimukset.

Tavoitteena on tosiaan tehdä ohjelma, joka etsii lyhyimmän reitin ulos labyrintistä tai kahden labyrintin pisteen välillä mahdollisimman
nopeasti. A* ja dijkstra ovat hyvin samanlaisia algoritmejä ja on mielenkiintoista nähdä miten paljon niiden tehokkuudella on eroa.
Bellman-Ford olisi kiva toteuttaa, koska sen pitäisi olla huomattavasti hitaampi kuin A* ja dijkstra ainakin kun labyrintin koko kasvaa.
A* höystettynä jump point searchilla voisi olla myös mielenkiintoista toteuttaa, jos aikaa riittää vaikkakin siitä saatava hyöty on
paljon suurempi avarammassa ruudukossa, jossa ei ole niin paljon esteitä kun labyrintissä. Valitsin algoritmeiksi A* koska sen pitäisi
olla aika lailla optimaalinen juuri labyrintin ratkaisemiseen, syystä että labyrintissä on paljon vähemmän mahdollisuuksia haarautua kuin
avarassa ruudukossa. Labyrintin generointiin valitsin Kruskalin ja Primin algoritmit, koska ne vaikuttivat tehokkailta tavoilta luoda
labyrinttejä. Kolmantena vaihtoehtona olisi satunnaseen syvyyssuuntaiseen läpikäyntiin perustuva labyrintin generointi, koska se muodostaisi
hyvin eri näköisiä labyrinttejä verrattuna Kruskalin ja Primin algoritmeihin, jotka molemmat perustuvat pienimpään virittävään puuhun.

Prioriteettijonon toteutukseen valitsin pairin keon, koska vaikka sen teoreettinen aikavaativuus on decrease keyn osalta huonompi kuin 
fibonacci keolla, niin on se silti yleensä käytännössä nopeampi ratkaisu. Disjoint-setin valitsin siksi, että se pystyy suorittamaan
jokaisen joukon yhdistämisen ja etsintä operaation lähes vakio ajassa. 

Tarkoituksena olisi tehdä ohjelmaan graafinen käyttöliittymä käyttäen javafx grafiikkakirjastoa. Jos vain aikaa riittää niin teen
ohjelmaan labyrintin import/export ominaisuuden, jolla voi antaa sovellukselle itse tekemiä labyrinttejä tai käyttää sovelluksen
generoimia labyrinttejä muualla. Muuten sovelluksen saamat syötteet ovat vain asetusten valintoja kuten näytä eri algoritmien
läpikäydyt reitit tai vaihda labyrintin generointi algoritmia ym.

Dijkstran aikavaativuuden tavoite on O((|E|+|V|)log|V|), missä E on kaarien määrä eli eri reittien määrä labyrintin ruudusta toiseen ja
V on solmujen määrä eli labyrintin ruutujen määrä. Tilavaativuus on O(|V|). Oletettaen, että A* algoritmin heuristiikkafunktio on
vakioaikainen, on A* aikavaativuus sama, kuin Dijkstran algoritmillä. Labyrintin generoinissa Kruskalin käyttämän Disjoint-setin
aikavaativuus on O(V log(V)) ja tilavaativuus O(V). 


#####Lähteet
- [Wikipedia: Pairing heap](https://en.wikipedia.org/wiki/Pairing_heap)
- [Wikipedia: Maze generation algorithm](https://en.wikipedia.org/wiki/Maze_generation_algorithm)
- [Tietorakenteet ja algoritmit luentomateriaali kevät 2016](https://www.cs.helsinki.fi/u/jkivinen/opetus/tira/k16/luennot.pdf)
- [Stackoverflow: Implementation of A* algorithm in Java](http://stackoverflow.com/questions/4624924/implementation-of-a-star-a-algorithm-in-java)
- [Jump point search](https://harablog.wordpress.com/2011/09/07/jump-point-search/)
