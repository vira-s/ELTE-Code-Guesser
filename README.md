# ELTE-Code-Guesser

A j�t�k elind�t�sa:
Nyissuk meg a parancssort �s g�pelj�k be az mvn clean install parancsot, majd csak ezek ut�n futtassuk a .../ELTE-Code-Guesser/src/codeguesser_presenter/target/ mapp�ban a: java -jar codeguesser_presenter.jar, ha pedig az ELTE-Code-Guesser mapp�ban �llunk, akkor �gy tudjuk futtatni a .jar f�jlt: java -jar src/codeguesser_presenter/target/codeguesser_presenter.jar

A j�t�k l�nyege:
A j�t�k m�k�d�se megfelel az angol MasterMind nev� j�t�knak, melyben sz�neket kell eredetileg kital�lni, a mi programunkban viszont sz�mokat kell kital�lni. A j�t�kban az a feladatunk, hogy a sz�m�t�g�p �ltal random kigener�lt sz�mokat �s a sz�msorban val� hely�ket kell kital�lni. T�bb neh�zs�gi foka is van, melyek a k�vetkez�k:
- k�nny�: ha ezt a m�dot v�lasztjuk, akkor megkapjuk, hogy az �ltalunk be�rt sz�msorban h�ny darab sz�m volt megfelel� �s j� helyen vagy megfelel� �s rossz helyen vagy nem tartalmazta a sz�msor azokat a sz�mokat
- k�zepes: ha ezt a m�dot v�lasztjuk, akkor megkapjuk azt, hogy h�ny darab sz�mot tartalmazott a sz�msor a tippelt sz�mok k�z�l vagy nem tartalmazza
- neh�z: ha ezt a m�dot v�lasztjuk, akkor az �ltalunk be�rt sz�msorb�l csak azt kapjuk meg, hogy h�ny sz�m van benne a sz�msorban �s m�g j� helyen is van
A j�t�k akkor �r v�get, ha kital�ljuk a sz�msort �s nyer�nk vagy elfogynak a l�p�seink �s elvesz�tj�k a j�t�kot.

A j�t�k haszn�lata:
A j�t�kot �gy tudjuk elind�tani, hogy megnyomjuk az �Options� gombot, ahol el�sz�r kiv�laszthatjuk a j�t�k neh�zs�g�t, majd megadhatjuk, hogy h�ny sz�mot szeretn�nk kital�lni (3-10 sz�mjegyig mehet�nk), ezen adatok bevitele ut�n megjelenik a j�t�k, ahol az ablak alj�n tudjuk beadni a tippjeinket, �s az eggyel feljebbi ablakban l�thatjuk, hogy miket tippelt�nk �s melyek voltak j�k vagy �ppen rosszak. A l�p�seink sz�ma pedig a k�d hossz�nak k�tszeres�ben plusz m�g a fel�ben van meghat�rozva, ha az utols� l�p�sre se tal�ljuk ki a g�p �ltal gener�lt sz�mot, akkor elvesztj�k a j�t�kot, de b�rmikor lehet �jat kezdeni.

Tesztesetek:
- �res beadott sz�msor eset�n: nem t�rt�nik semmi, nem cs�kken a l�p�sek sz�ma sem
- Bet�sor eset�n: nem t�rt�nik semmi, nem cs�kken a l�p�sek sz�ma sem
- Hosszabb sz�msor be�r�sa: nem enged�lyezi a programunk, hogy a megengedettn�l t�bb sz�mot vigy�nk fel

A Buildeket itt lehet el�rni:
https://travis-ci.org/virabia/ELTE-Code-Guesser

K�perny�tervet tal�ltok m�g a mapp�ban.
UML Diagramok: Use Case, Class Diagramok is tal�lhat�ak m�g a mapp�ban.

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

Az oszt�lyok lefedetts�g�nek m�rt�ke:
src/codeguesser_presenter/target/site/jacoco/index.html




