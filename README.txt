Nicuta Loredana Ionela 325CD

Abordarea acestei probleme a fost impartita in mai multe clase; Cele principale
sunt :
	1. clasa Player. Aceasta va contine toate elementle necesare fiecarui player
	individual, cele doua abilitati, power1 si power2 ce vor fi modificate 
	ulterior in functie de tipul de jucator; Pe langa aceasta, mai sunt cateva 
	metode ce vor fi folosite la toti jucatorii si anume calculul amplificatori-
	lor de teren, respectiv cei de rasa. Mai exista metode de calcul al nivelului,
	al pragului de xp pentru levelUp, minim, maxim. Clasele "mostenitoare" sunt:
		1.1 Pyromancer, un tip de jucator ce are drept power1 Fireblast, 
		respectiv Ignite drept power2;
		1.2 Knight, un tip de jucator ce are drept power1 Execute, respectiv 
		Slam drept power2;
		1.3 Wizard, un tip de jucator ce are drept power1 Drain, respectiv 
		Deflect drept power2;
		1.4 Rogue, un tip de jucator ce are drept power1 Backstab, respectiv 
		Paralysis drept power2;
		
		Pentru fiecare jucator pasii urmati in calcularea unui damage sunt:
		- calcularea damagelui de baza, respectiv cel overtime asociat fiecarui
		nivel;
		- calcularea procentelor (Wizard) si a anumitor limite (ex. hpLimit la
		Knight)
		- calcularea amplificatorilor specifici tipului de teren(ex amplifyLand), 
		respectiv al tipului de oponent(amplifyRace) si a celui extra (ex 
		amplifyHits la Rogue)
		- calcularea damage-ului final prin aplicarea tuturor modificatorilor
		damage-ului calculat anterior;
		
	2. clasa Land. Aceasta va contine toate detaliile necerare fiecarei "parcele"
	de teren de pe harta; Astfel, harta jocului va fi o matrice cu elemente de 
	tip Land , ce va retine jucatorii specifici zonei, tipul de teren, si un 
	constructor ce va initializa fiecare parcela de teren cu jucatorul specific;
	
	3. clasa Main. Aceasta va reprezenta desfasurarea jocului. Vom citi din 
	fisier harta formata din tipurile de teren, jucatorii si pozitiile pe harta
	corespunzatoare, iar mai apoi incep rundele;
		- citim un sir de caractere, ce reprezinta miscarile jucatorilor si 
		actualizam noile coordonate (player,row, player.column);
		- inainte de parcurgerea hartii, actualizam viata (hp) in functie de 
		damageul Overtime (player.damageOvertime). Daca jucatorul, in urma 
		aplicarii unui damageOvertime atunci, acesta moare deci este eliminat
		de pe harta;
		- se parcurge intreaga harta, iar atunci cand ajungem intr-o zona cu 
		2 jucatori, acestia se vor bate, aplicand fiecare cele 2 abilitati
		(player.power1, player.power2) oponentului; Daca moare unul dintre 
		ei atunci este eliminat de pe harta, iar celalalt player este declarat
		invingator al bataliei, modificand xp-ul, hp-ul respectiv nivelul in cazul
		in care xp-ul a atins un prag corespunzator unui nivel mai mare;
		- daca dupa aplicarea abilitatilor, amandoi jucatorii mor atunci acestia
		vor fi doar eliminati de pe harta, fara a li se aduce vreo modificare 
		asupra xp-ului sau a hp-ului;
		- la final se scriu in fiesierul de iesire, detalii despre fiecare
		jucator; daca este mort, atunci se va afisa numai numele, impreuna cu 
		sirul "dead", altfel se va afisa numele, nivelul, xp-ul, pozitiile pe
		harta (player.row/player.column);
		
		