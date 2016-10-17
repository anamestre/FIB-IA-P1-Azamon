# Azamon

## To do

* Començar a implementar classes de l'AIMA
	* AzamonHeuristicFunction1: heurístic que té en compte minimització de costos d'emmagatzematge i transport.
	* AzamonHeuristicFunction2: heurístic que té en compte, a més dels factors del primer heurístic, la felicitat dels consumidors (dies de diferència amb què ha arribat un paquet).
	* AzamonProblem: classe "gestora" de les cerques per HC i SA (cal implementar impressió dels resultats).
	*AzamonSuccessorFunction: classe generadora dels estats successors.
	*AzamonGoalTest: **IMPLEMENTADA**

## Explicacions

* **Generació de la solució inicial 1**: s'ordenen els paquets per prioritat ascendent (0, 1, 2) i s'assignen a les ofertes de com a mínim la prioritat corresponent (es podria dir que pren prioritat la prioritat *haha*).
* **Generació de la solució inicial 2**: s'ordenen els paquets i les ofertes per prioritat descendent (2, 1, 0) i s'assignen a les ofertes de la prioritat corresponent (ja que com més dies de transport té una oferta més barat és el transport, es podria dir que pren prioritat el preu).
