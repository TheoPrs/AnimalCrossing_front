package com.example.animalcrossing.viewmodel
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlin.random.Random



class AnimalFactViewModel : ViewModel() {

    private val animalFacts = listOf(
        "Le guépard est l'animal terrestre le plus rapide, pouvant atteindre des vitesses de 110 km/h.",
        "Les éléphants sont les seuls animaux qui ne peuvent pas sauter.",
        "Les pieuvres ont trois cœurs et leur sang est bleu.",
        "Le narval est souvent surnommé 'licorne des mers' en raison de sa longue défense torsadée.",
        "Les girafes dorment seulement 30 minutes par jour, et souvent debout.",
        "Les dauphins donnent des noms à leurs congénères en utilisant des sifflements spécifiques.",
        "Les pandas passent 12 à 14 heures par jour à manger du bambou.",
        "Les kangourous ne peuvent pas marcher en arrière.",
        "Les cochons sont incapables de transpirer. C’est pourquoi ils aiment se rouler dans la boue pour se rafraîchir.",
        "Les paresseux sont si lents que des algues peuvent pousser sur leur fourrure.",
        "Les fourmis peuvent soulever jusqu'à 50 fois leur poids corporel.",
        "Le cri de la girafe est si doux qu'il est quasiment imperceptible par l’oreille humaine.",
        "Les flamants roses deviennent roses en mangeant des crustacés riches en caroténoïdes.",
        "Les hippocampes sont monogames et s’accouplent pour la vie.",
        "Le cœur d'une baleine bleue est si grand qu'un humain pourrait y ramper à l'intérieur.",
        "Les corbeaux sont extrêmement intelligents et peuvent résoudre des problèmes complexes.",
        "Les moustiques sont responsables de plus de décès humains que tout autre animal sur Terre.",
        "Les loutres de mer se tiennent la main pour ne pas se séparer pendant leur sommeil.",
        "Les abeilles communiquent entre elles en dansant pour indiquer la direction des sources de nourriture.",
        "Les caméléons changent de couleur non seulement pour se camoufler, mais aussi pour exprimer leurs émotions.",
        "Les tortues de mer femelles reviennent toujours pondre leurs œufs sur la même plage où elles sont nées.",
        "Les éléphants peuvent pleurer et sont connus pour enterrer leurs morts.",
        "Les chauves-souris sont les seuls mammifères capables de voler.",
        "Les chats domestiques peuvent courir jusqu'à 48 km/h sur de courtes distances.",
        "Les crocodiles peuvent vivre jusqu'à 100 ans.",
        "Le poisson-clown est hermaphrodite et peut changer de sexe si nécessaire.",
        "Les pythons peuvent jeûner pendant plus d'un an sans problème de santé.",
        "Les bébés kangourous, appelés joeys, sont aussi petits qu'une cacahuète à la naissance.",
        "Les coraux sont des animaux, pas des plantes, et jouent un rôle crucial dans l'écosystème marin.",
        "Le cerveau d'une pieuvre contient autant de neurones qu'un cerveau de chien.",
        "Les grenouilles ne boivent pas d'eau ; elles l'absorbent par leur peau.",
        "Les lions mâles dorment environ 20 heures par jour.",
        "Les requins sont plus anciens que les dinosaures.",
        "Les fourmiliers n’ont pas de dents et utilisent leur longue langue collante pour attraper les insectes.",
        "Les koalas dorment jusqu'à 22 heures par jour pour économiser de l'énergie.",
        "Les escargots peuvent dormir jusqu'à trois ans d'affilée.",
        "Les hippocampes mâles sont ceux qui portent et accouchent des bébés.",
        "Les lapins mangent leurs propres excréments pour extraire tous les nutriments.",
        "Les pigeons peuvent retrouver leur chemin vers leur nid à des centaines de kilomètres de distance.",
        "Les poissons rouges peuvent voir plus de couleurs que les humains.",
        "Les hippocampes sont des nageurs très lents et sont souvent emportés par les courants marins.",
        "Le bec d'un calmar géant est aussi fort qu'un bec de perroquet.",
        "Les chats ont cinq doigts sur leurs pattes avant et seulement quatre sur leurs pattes arrière.",
        "Les baleines à bosse communiquent en chantant, et leurs chansons peuvent être entendues sur des centaines de kilomètres.",
        "Les tigres ont des rayures uniques, tout comme les empreintes digitales des humains.",
        "Les grenouilles poison-dard sont parmi les animaux les plus venimeux au monde.",
        "Les requins n'ont pas d'os. Leur squelette est fait de cartilage.",
        "Les papillons goûtent avec leurs pattes.",
        "Les hiboux ont des yeux fixes et ne peuvent pas les bouger. Ils tournent donc leur tête jusqu'à 270 degrés.",
        "Les otaries sont capables de reconnaître les voix humaines, notamment celle de leurs dresseurs.",
        "Les corbeaux peuvent se souvenir des visages humains et apprendre à éviter ceux qui leur causent du tort.",
        "Le renard arctique peut survivre à des températures aussi basses que -70 °C.",
        "Les lémuriens utilisent leur queue pour se communiquer et marquer leur territoire.",
        "Les méduses sont composées à 95 % d'eau.",
        "Le lynx roux peut voir huit fois mieux que les humains, ce qui l'aide à chasser la nuit.",
        "Les grenouilles peuvent respirer par leur peau lorsqu'elles sont sous l'eau.",
        "Les harfangs des neiges ont des plumes qui leur permettent de voler sans bruit, ce qui en fait des chasseurs redoutables.",
        "Le papillon monarque est capable de parcourir des milliers de kilomètres lors de sa migration.",
        "Les chimpanzés partagent environ 98 % de leur ADN avec les humains.",
        "Les crabes peuvent régénérer leurs pinces s'ils les perdent.",
        "Les tortues géantes des Galápagos peuvent vivre plus de 150 ans.",
        "Le pic-vert peut frapper un arbre jusqu'à 20 fois par seconde sans se blesser.",
        "Les manchots mâles incubent les œufs pendant que les femelles chassent pour trouver de la nourriture.",
        "Les éléphants communiquent avec des infrasons, qui sont trop graves pour être entendus par les humains.",
        "Les castors utilisent leur queue comme gouvernail lorsqu'ils nagent.",
        "Les serpents peuvent sentir les vibrations du sol à travers leur langue.",
        "Le grand requin blanc peut détecter une goutte de sang dans 25 litres d'eau.",
        "Les coraux phosphorescents brillent dans le noir en raison des pigments fluorescents qu'ils produisent.",
        "Les dauphins dorment en fermant un seul hémisphère de leur cerveau à la fois pour pouvoir rester en mouvement.",
        "Les flamants roses peuvent dormir debout sur une seule patte pendant de longues périodes.",
        "Les hippocampes mâles prennent soin des bébés après leur naissance.",
        "Les aigles royaux peuvent repérer un petit mammifère à plus de 3 kilomètres de distance."
    )


    private val _animalFact = MutableStateFlow<String?>(null)
    val animalFact: StateFlow<String?> = _animalFact.asStateFlow()

    fun fetchAnimalFact() {
        val randomFact = animalFacts[Random.nextInt(animalFacts.size)]
        _animalFact.value = randomFact
    }
}
