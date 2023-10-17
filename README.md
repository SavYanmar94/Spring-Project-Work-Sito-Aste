## Project Work - Sito di Aste Online

*Link della presentazione* : [clicca qui](https://www.canva.com/design/DAFvjZ2M-sE/aXOuneWuF9lojUDO1hB0Ng/edit)

**Descrizione Incarico** : Viene commissionata al team, la realizzazione di una piattaforma di aste online attraverso la quale, Utenti non commerciali, possano proporre in vendita i propri beni e/o formulare offerte finalizzate all'acquisto di beni di altri.

**Struttura Progetto** 
 - *Front End Web Application* - sviluppata mediante Angular Framework 
 - *Back End Web Service* - sviluppato mediante Spring Boot Project in correlazione con database MySql
 
 **Funzionalità primarie richieste** 
 - *Home Page Piattaforma*: La piattaforma dovrà presentare una home page di libera navigazione nella quale visualizzare gli articoli disponibili messi in asta dai vari Utenti Venditori. Per ciascun articolo dovrà essere visualizzabile una scheda di dettaglio attraverso la quale un Utente, purchè loggato, possa inviare un'offerta di acquisto.
 - *Logica Offerte di acquisto*:Le offerte di acquisto formulate dagli Utenti, dovranno seguire logica d'asta e pertanto, non potranno essere inferiori nè al prezzo stabilito dal Venditore, nè al valore dell'ultima offerta. Verrà data facoltà all'acquirente di inviare più offerte per il medesimo articolo purchè nel rispetto dei criteri di cui sopra e non dovrà essere concessa, ad un Utente, la possibilità di registrare offerte per i suoi stessi articoli.
 - *Registrazione e Login Utente*:Per poter usufruire della piattaforma, un Utente dovrà creare il proprio profilo indicando dati anagrafici completi, nickname e password. I dati dovranno essere soggetti a validazione generale e, i nickname, dovranno essere univoci. Le attività dell'Utente saranno soggette a login e dovrà essere implementato un sistema di protezione Front-Back basato su token autorizzativo. Ciascun Utente dovrà poter effettuare il logout.
 - *Gestione Profilo Utente* : In sede di registrazione, un Utente potrà scegliere tra profilo Venditore e profilo Acquirente. Il profilo Venditore permetterà all'Utente di collocare in asta i propri articoli oltrechè di formulare offerte di acquisto per articoli di altri; il profilo Acquirente invece, offrirà all'Utente la sola possibilità di formulare offerte di acquisto.
 - *Dashboard Utente* : Una volta loggato, ciascun Utente, potrà accedere ad una propria dashboard dall'ambito della quale, a seconda del tipo di profilo registrato, avrà possibilità di visualizzare e modificare i propri dati personali (tranne nickname e tipo di profilo), visualizzare l'elenco dei propri articoli in asta, dei propri articoli venduti e delle proprie offerte di acquisto oltrechè di inserire nuovi articoli per la vendita.
 - *Gestione Articoli in asta* : Un Utente, purchè Venditore, potrà mettere in asta i suoi articoli indicando, per ciascuno, nome, descrizione, categoria (o tag identificativi), importo base d'asta e un'immagine (opzionale). Un Utente avrà facoltà di ritirare un proprio articolo purchè non ancora venduto ma non avrà facoltà di modificare i dati di un articolo già in asta.
 - *Logica di vendita*: Un articolo in asta, si considera venduto nel momento in cui, il Venditore, accetta una delle offerte di acquisto ricevute. Al verificarsi di tale evento, l'Acquirente la cui proposta è stata accettata, dovrà vedere lo stato della stessa passare da “In Corso” ad “Accettata” mentre, eventuali altri proponenti, vedranno lo stato delle loro proposte passare da “In Corso” a “Rifiutata”. L'articolo venduto non dovrà più comparire in home page.
