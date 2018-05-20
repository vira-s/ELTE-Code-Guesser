# ELTE-Code-Guesser

A játék elindítása:
Nyissuk meg a parancssort és gépeljük be az mvn clean install parancsot, majd csak ezek után futtassuk a .../ELTE-Code-Guesser/src/codeguesser_presenter/target/ mappában a: java -jar codeguesser_presenter.jar, ha pedig az ELTE-Code-Guesser mappában állunk, akkor így tudjuk futtatni a .jar fájlt: java -jar src/codeguesser_presenter/target/codeguesser_presenter.jar

A játék lényege:
A játék mûködése megfelel az angol MasterMind nevû játéknak, melyben színeket kell eredetileg kitalálni, a mi programunkban viszont számokat kell kitalálni. A játékban az a feladatunk, hogy a számítógép által random kigenerált számokat és a számsorban való helyüket kell kitalálni. Több nehézségi foka is van, melyek a következõk:
- könnyû: ha ezt a módot választjuk, akkor megkapjuk, hogy az általunk beírt számsorban hány darab szám volt megfelelõ és jó helyen vagy megfelelõ és rossz helyen vagy nem tartalmazta a számsor azokat a számokat
- közepes: ha ezt a módot választjuk, akkor megkapjuk azt, hogy hány darab számot tartalmazott a számsor a tippelt számok közül vagy nem tartalmazza
- nehéz: ha ezt a módot választjuk, akkor az általunk beírt számsorból csak azt kapjuk meg, hogy hány szám van benne a számsorban és még jó helyen is van
A játék akkor ér véget, ha kitaláljuk a számsort és nyerünk vagy elfogynak a lépéseink és elveszítjük a játékot.

A játék használata:
A játékot úgy tudjuk elindítani, hogy megnyomjuk az „Options” gombot, ahol elõször kiválaszthatjuk a játék nehézségét, majd megadhatjuk, hogy hány számot szeretnénk kitalálni (3-10 számjegyig mehetünk), ezen adatok bevitele után megjelenik a játék, ahol az ablak alján tudjuk beadni a tippjeinket, és az eggyel feljebbi ablakban láthatjuk, hogy miket tippeltünk és melyek voltak jók vagy éppen rosszak. A lépéseink száma pedig a kód hosszának kétszeresében plusz még a felében van meghatározva, ha az utolsó lépésre se találjuk ki a gép által generált számot, akkor elvesztjük a játékot, de bármikor lehet újat kezdeni.

Tesztesetek:
- Üres beadott számsor esetén: nem történik semmi, nem csökken a lépések száma sem
- Betûsor esetén: nem történik semmi, nem csökken a lépések száma sem
- Hosszabb számsor beírása: nem engedélyezi a programunk, hogy a megengedettnél több számot vigyünk fel

A Buildeket itt lehet elérni:
https://travis-ci.org/virabia/ELTE-Code-Guesser

Képernyõtervet találtok még a mappában.
UML Diagramok: Use Case, Class Diagramok is találhatóak még a mappában.

Coverage:
[INFO] Analyzed bundle 'codeguesser_presenter' with 7 classes
[INFO] All coverage checks have been met.
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ codeguesser_presenter ---
[INFO] Building jar: /home/travis/build/virabia/ELTE-Code-Guesser/src/codeguesser_presenter/target/codeguesser_presenter.jar
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.1:report (jacoco-site) @ codeguesser_presenter ---
[INFO] Loading execution data file /home/travis/build/virabia/ELTE-Code-Guesser/src/codeguesser_presenter/target/coverage-reports/jacoco-unit.exec
[INFO] Analyzed bundle 'codeguesser_persistence' with 7 classes

Az osztályok lefedettségének mértéke:
src/codeguesser_presenter/target/site/jacoco/index.html




