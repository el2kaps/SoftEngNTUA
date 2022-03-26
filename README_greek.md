# TL20-67

# Εργασία στο μάθημα Τεχνολογία Λογισμικού<br>
Σχολή Ηλεκτρολόγων Μηχανικών και Μηχανικών Υπολογιστών, ΕΜΠ<br>
7ο εξάμηνο 2020-2021<br>
# Θέμα: Ανάπτυξη πληροφοριακού συτήματος για τη διαχείριση της φόρτισης ηλεκτρικών οχημάτων<br>

Ομάδα "SmartEMobility"<br>
Αχλάτης Στέφανος-Σταμάτης (03116149)<br>
Καψάλη Ελένη-Ελπίδα (03117817)<br>
Χριστοφίδη Γεωργία (03117015)<br>


Το REST API είναι διαθέσιμο στο ακόλουθο base URL: https://localhost:8765/evcharge/api/  <br>

**Technology Stack - Tεχνολογίες που χρησιμοποιήθηκαν**<br>
* Java 11<br>
* Groovy για το testing<br>
* Gradle build tool<br>
* Spock testing framework<br>

**Back-end**<br>
* Restlet framework<br>
* MySQL Connector/J<br>
* Spring JDBC<br>
* Για την εκτέλεση του back-end: <br>
cd back-end <br>
./gradle build
./gradlew apprun <br>

**CLI client**<br>
* Picocli<br>
* Junit<br>
* Rest api client<br>
* Για την εκτέλεση του CLI:<br>
cd cli-client<br>
./gradle build
./gradlew installDist
./build/install/evcharge_67/bin/evcharge_67 -h

**Front-end**<br>
* React
* CRA (Create-React-App)
* Για την εκτέλεση του frontend:<br>
cd front-end<br>
yarn<br>
yarn start

**Αρχιτεκτονική του συστήματος**
![image](https://user-images.githubusercontent.com/63153771/111076739-391daf00-84f6-11eb-93ea-23bcc1363c04.png)



