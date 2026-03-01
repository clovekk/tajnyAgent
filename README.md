# :information_source: Popis hry
Jedná se o jednoduchou a krátkou textovou hru s příběhem jednoduchým na pochopení.
Hráč hraje za Agenta Martina Starého, což je člen státní tajné služby, 
právě se nachází před vchodem do tajného podzemního tunelu využívaného protistátním odbojem 
a má za úkol v přestrojení proniknout do jádra základny odboje a zatknout jejího velitele.
Aby se mu toto povedlo bude muset splnit několik jednoduchých úkolů, aniž by vzbudil příliš velké podezření.

# :joystick: Příkazy
| Příkaz                     | Využití                                    |
| -------------------------- | ------------------------------------------ |
| konec                      | ukončí a uloží hru                         |
| prikazy                    | vypíše příkazy                             |
| napoveda                   | vypíše nápovědu k aktuální situaci         |
| zakresli                   | zakreslí pokoj do mapy pokud ji hráč má    |
| jdi-\<jmeno lokace\>       | posune hráče do dané místnosti             |
| seber-\<jmeno predmetu\>   | hráč sebere daný předmět                   |
| proheldej                  | prohledá aktuální místnost                 |
| spi                        | hráč půjde do rána spát                    |
| mluv-\<jmeno postavy\>     | promluví s danou postavou                  |
| ukoly                      | zobrazí seznam úkolů                       |
| zahod-\<jmeno predmetu\>   | zahodí daný předmět pokud to je dovolené   |
| pouzij-\<jmeno predmetu\>  | použije daný předmět pokud to je možné     |
| cekej                      | hráč počká 6 hodin                         |

# :warning: Důležité informace
Při spouštěni hry pomocí příkazového řádku je nutné aby jste pokaždé jar soubor spouštěli ze složky ve které se nachází.
Důvod to má takový, že při ukládání hry se složka s uloženou hrou vytvoří ve složce ze které je příkazový řádek spuštěn a ne ve složce kde se nachází samotný jar soubor.
Na vhodné spuštění hry se tedy před jejím spuštěním ujistěte, že jste v příkazovém řádku pomocí příkazu cd nastavili složku jar souboru hry.
Pokud toto nedodržíte, je možné že se složka s uloženou hrou vytvoří v na nechtěné lokaci, nebo že se vám nenačte naposledy uložená hra.
