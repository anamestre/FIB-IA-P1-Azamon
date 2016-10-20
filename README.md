# Azamon

## To do

* Acabar d'implementar les classes de l'AIMA
	* AzamonHeuristicFunction1: **IMPLEMENTADA**
	* AzamonHeuristicFunction2: estamos en ello
	* AzamonProblem:
		* Part de Hill Climbing: **IMPLEMENTADA**
		* Part de Simulated Annealing: **GAIREBÉ IMPLEMENTADA perquè explota imprimint les accions**
	* AzamonSuccessorFunction:
		* Versió moure/permutar: **IMPLEMENTADA**
		* Versió afegir/treure: *preguntar al profe*
	* AzamonGoalTest: **IMPLEMENTADA**
* Fer els experiments **IMPORTANT L'EXPERIMENT ESPECIAL**

## Explicacions

* **Generació de la solució inicial 1**: s'ordenen els paquets per prioritat ascendent (0, 1, 2) i s'assignen a les ofertes de com a mínim la prioritat corresponent (es podria dir que pren prioritat la prioritat *haha*).
* **Generació de la solució inicial 2**: s'ordenen els paquets i les ofertes per prioritat descendent (2, 1, 0) i s'assignen a les ofertes de la prioritat corresponent (ja que com més dies de transport té una oferta més barat és el transport, es podria dir que pren prioritat el preu).
