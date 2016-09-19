#Viikkoraportti 2

#### Tehty tällä viikolla
- Graafisen käyttöliittymän pohja
- Ensimmäinen labyrintin generointialgoritmi
- Enismmäinen labyrintin ratkaisualgoritmi


#### Ohjelman edistyminen
Ohjelma on edistynyt ihan kohtuullista vauhtia. Olisin halunnut saada toisenkin ratkaisualgoritmin toteutettua tämän viikon palautukseen,
mutta käytettävä aika loppui kesken. Ensimmäisen algoritmin toteuttaminen oli yllättävän yksinkertaista.

Tällä viikolla olen oppinut toteuttamaan labyrintin lyhyimmän ratkaisun etsivän algoritmin käyttäen leveyssuuntaista läpikäyntiä. Testaillessani
kuinka paljon aikaa mikäkin sovelluksen osa vie huomasin, että Labyrintin generointi vie tällä hetkellä huomattavasti paljon kauemmin kuin 
sen ratkaiseminen. Labyrintin koon ollessa generoitaessa 1000x1000 ja ratkaistaessa 2001x2001 (tietorakennetta vaihdetaan generoimisen yhteydessä)
menee generoimiseen aikaa 130 000ms, tietorakenteen vaihtamiseen 50ms ja labyrintin ratkaisemiseen bfs:llä 170ms. Kun saan kaikki ratkaisualgoritmit
tehtyä, yritän optimoida labyrintin generoimista paremmin.

Tällä viikolla eniten vaikeuksia tuotti labyrintin toString metodin toteutus, jonka halusin toimimaan nähdäkseni toimiiko labyrintin generointi.
Sen jälkeen siirryin heti graafiseen käyttöliittymään ja kesti jonkin verran löytää keino luoda kuva suoraan datasta. Käytin ensin tekstuureja ja
ja toString metodin tavoin jokainen labyrintin ruutu koostui 3x3 ruudusta (15px15p). Näin ollen labyrintistä tuli nopeasti liian suuri ja 
kaikki sisäseinät olivat 2x paksumpia kuin käytävät, sillä jos ruutujen välissä oli seinä, niin kumpikin ruutu piirsi oman seinänsä.
Tekstuurien suuri määrä aiheutti myös huomattavaa pätkimistä, kun labyrinttiä yritti raahata. Tästä johtuen kehitin algoritmin, jolla
sain tallennettua labyrintin kaksiuloitteiseen taulukkoon.

Seuraavaksi eniten päänvaivaa tuotti löytää keino luoda uuden kaksiuloitteisen taulukon datasta suoraan yksi kuva. Lopulta löysin javaFX:stä
writableImage- luokan ja siihen [oraclen esimerkin](http://docs.oracle.com/javafx/2/image_ops/jfxpub-image_ops.htm), josta opin käyttämään kyseistä luokkaa.
Uusi ratkaisu loi edellistä pienemmän ja tarkemman kuvan labyrintista, jota tällä kertaa pystyi jopa raahaamaan ilman, että joutui odottamaan
sekunteja ennen kuin kuva päätti siirtyä. :)

Seuraavaksi olisi tarkoitus tehdä loput ratkaisualgoritmit, kehittää vähän käyttöliittymää ja sitten ruveta työstämään omia tietorakenteita.
