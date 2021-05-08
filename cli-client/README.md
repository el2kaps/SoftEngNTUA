# CLI client

Ενδεικτικά περιεχόμενα:

- Command line interface (CLI).
- CLI functional tests.
- CLI unit tests.

Ενδεικτικές εντολές για τη λειτουργία του CLI:<br>
./build/install/evechicle_67/bin/evechicle_67 healthcheck<br><br>
./build/install/evechicle_67/bin/evechicle_67 ressetsessions<br><br>
./build/install/evechicle_67/bin/evechicle_67 login --username admin --passw petrol4ever<br><br>
./build/install/evechicle_67/bin/evechicle_67 logout<br><br>
./build/install/evechicle_67/bin/evechicle_67 SessionsPerPoint --point  point_0236d532c86d4ab090679de8ac1ee78a --datefrom 20160503 --dateto 20220904 --format json --apikey RiJHRaBOaXMUpoRR <br><br>
./build/install/evechicle_67/bin/evechicle_67 SessionsPerProvider --provider charging_program_01d9144caca94bebb5b55bb278cde93c --datefrom 20190811 --dateto 20210510 --format csv --apikey RiJHRaBOaXMUpoRR<br><br>
./build/install/evechicle_67/bin/evechicle_67 Admin --usermod --username john --passw smith<br><br>

(Οι τιμές των παραμέτρων username, passw, point, provider, datefrom, dateto και format αντιστοιχούν σε πραγματικές τιμές από τη βάση δεδομένων μας. 
Η τιμή της παραμέτρου apikey είναι τυχαία. Πραγματικές τιμές δεν μπορούν να παρατεθούν, διότι παράγονται κατά το login του χρήστη, συνδέονται μοναδικά 
με αυτόν και εξασφαλίζουν την ταυτοποιημένη πρόσβαση του στα δεδομένα.)
