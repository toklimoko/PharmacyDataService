insert into category(id,name, description) values
(1,'Przeciwbólowe','Lekarstwa przeciwbólowe, przeciwgorączkowe, przeciwzapalne'),
(2,'Serce','Lekarstwa na nadciśnienie, zaburzenia rytmu'),
(3,'Antybiotyki','Antybiotyki różnej maści');


insert into medicines(name,substance,category_id,dose,units,packages) values
('Azycyna','Azithromycinum',3,'500 mg', 3,10),
('Polfenon','Propafenon chlorowodorku',2,'150 mg', 60,14),
('Apap','Paracetamolum',1,'500 mg', 24,30);