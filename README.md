##WARNING: 
###This tool writes direct to the selected disk. If you select the wrong disk all contents on the selected dips will be destroyed. 
 
# USBBoot 
Simple bootable USB stick creation tool for Mac OS X in Java. 
It is very simple to create a bootable USBStick with Mac OS X.  
It is just a few commands on your command line and you have  
perfectly bootable USB Stick created from a .iso file. 
To make the functionality available for GUI users I developed this 
simple program.

![alt tag](https://raw.github.com/sbamamoto/USBBoot/master/src/main/resources/docimages/step1.png)

Just select a .iso file and press the Prepare button. Be patient it takes 
a while and has no progressbar yet.
![alt tag](https://raw.github.com/sbamamoto/USBBoot/master/src/main/resources/docimages/step2.png)
After the image has been created the program will scan for drives to install 
the image on. Be sure that your USB stick is inserted and the system has recognized it.
UBBoot will show all drives found be crefull when selecting the drive.
![alt tag](https://raw.github.com/sbamamoto/USBBoot/master/src/main/resources/docimages/step3.png)
After selectign the drive press the Write To Stick button to write the .iso file to the selected drive.
![alt tag](https://raw.github.com/sbamamoto/USBBoot/master/src/main/resources/docimages/step4.png)
Thats it the drive will be written.
###3rd Party
I used https://github.com/performantdesign/cocoasudo to get the neccessary privileges. The exceutable is embedded to make it easier for you to build a working .dmg file.
To create a .dmg file the project:  is used.
