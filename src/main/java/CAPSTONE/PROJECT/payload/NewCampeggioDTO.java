package CAPSTONE.PROJECT.payload;




import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

public record NewCampeggioDTO (



        Long postiDisp ,
        @NotEmpty(message = "indirizzo obbligatorio")
                /*[Numero civico] [Nome della via], [Città], [Provincia], [CAP], [Paese]*/
        /*@Pattern(regexp="^[0-9]+ [a-zA-Z\s]+, [a-zA-Z\s]+, [a-zA-Z\s]+, [0-9]{5}, [a-zA-Z\s]+$")*/
                String indirizzo ,
        String nome,
        @DefaultValue("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAilBMVEX///8AAAD8/PwEBAQMDAz4+Pjt7e1TU1Px8fH29vYgICA7OztHR0cXFxcICAgNDQ0xMTHn5+cUFBRkZGTf398bGxtbW1uZmZkjIyO6urqioqIzMzPR0dHb29uVlZWvr6+EhIR3d3djY2PHx8c/Pz9vb28qKiqrq6uOjo5/f39OTk6Dg4O3t7fCwsIFh/X7AAANwklEQVR4nO1diXravBKVZMtYBNvYYPYlhCWEhvd/vTtnxmT7k5IQA+V+Om0oW2wdzT6SXaU8PDw8PDw8PDw8PDw8PDw8PDw8PDw8PDw8aoFVyijjlDGGXuCBn1x7WD8HDRsjj+mpIzpMITbXHtWJsDTyGFzwYOlfc4MScSwNQz/Lu9UEekbvGXV7MhHTIDZ/tNbp1kCv7E0aSUR/rZq0tA4zrRtTJhFde1Q/h+PHx0SnJBEd6vZU5bFxVx7VSSBdetI6oD/4q9MZZHJDquXENshGopYOAhJIu61ZLgvYv3jlG7B5HqIlOtFkkGL82SqKVhlLpsUmErubiChWmQgyiYc0+ECnQc8Z43pQLh2QoSBCRjfhhjFSMur7EJaRBgvomlMLEg45r2Su2OBvgAdGTVJ5gFYFOttaxSZh+0Qr02nat+yYrz1IGPF7/OcLEWlO3CIeNPJi74hXHNODGZKIYPJPFhZyfZEcI2JUriZNXUAizWlcaRGNPJ5sSCDEpJerWyBirRoVQQi9uuuKM5ac3qgu5JQGmlKv+BpDFxjOXef92Qh/R7MR/dunJ9v44HEPNcc9SKShbsQyXleFeXrZ0CFsvlyyg1afKOZlYOMdqznpP9ltyC41ncdsz6oqPkwHtkEfrEEuwlinU4w5glzWcMnkvZ4t+Fe/eVmIFtGU0h9On2hIFLdDPRQSGBUhb9GU05zrmbKQg3VmnaZr4/ApPcxItwJSr62aqmt5YVhEL+Rsg4y2SDG5NPtDlBkyKEp279hdBeFcKkQLdSKQmlkpGuf0eUFCWZPvuprNG7fTwoQhXOY82fjJ1b6Q7GowMY4rdriqIqCRbyaxkrcmBU8EcSPtcldQLZ69HrFI+o+Pj8P5fP78QMoVQrUoSzQ2Uv0ggdVkbaiNi2ickwRc8ZNMaNARmhH5ncxFu3slYyd1V9CT8hAc1BZqNLfWSMb7RMII0kSvaMQw/EiNYPewJZj/CPEcKpY3yI7IToqhukJ9At0xqgcDJ1dE0YKS2C2MZHj4eI0YQUqzgFE7UjT4KKRbvV7A+coa2ge7MR0WSVD0r1SfEBHkT5FEFWX60JmhkZjQ0zLcreFeSSRmTl9YGLNgBYPJI8myxmzZ7engnlX24hr2KZE5PonyQYaRJaRpHP8itR8gYKTpCN5qBPPO9GAPJlBEcl5Qt6xHTjq+eKX1KZG9iZ1ZUhFFLHQJtY+hQvMEiqaTZwngzyjeyYDmUDkkkcQT+lX0cnP5hOUzIuEzPZsnEj4200NedQ9aCXkmJQ061W3LO/eH1mm3TNl9b7qXbxJ9KhGa8r6WcN9yXK3TfHc4a0Sia3mQ9Jj3+K2wg5xeoUxcUYghsVDmZS5cn3xBZME8Av2ANMUihbrLyADCgt0Ue1hxYkVIb2d3yLO4Fdxg7QvSx8vS+Eq1OjyxWv85dBKnJeJikG2rMCptE3rYZvhiUnK8hL514A8IswuXjJ9KpJR8WD8qnmin5iXrFXkoihi2apTKE7JwaFc5ZzlRHO1z1yslYV6DSI7nSDZmzCFA7E73sbR6ydOywdwtP7R6+dUSSSV9PFLSm4/3pQSbXbUMcWEivPIRqa3OOAiGetCVVZCIojkNNNO7iIf+JgWRWjHa0YchqpUIXsGp5YZbXsVYutyX8V+Gk8YwN9USzn1ZtUNbU8mtVL4rOGTfO27EvZ1jeu7oS+6ef6PY5Upyr7yl2eSb08uVjAcieE6TFy+0yOPBoPFOn0/HoU7ovX5VLb4bF7+K2VlTZhmOp0aJkDpsJkG2FIO6GJFUiMSkWZSBkNu55wUcGuK+4DI3pGokFrKvQTuuDIbSgAm8MKWMe4nxyv1JmQnccH4xIkFFBL4zlS67rRY9RwE34dpdy8tT7qOiGOPYWVmK8sjMghF0lH7VjVLJIbeX4fGGCEXqO1ZtKipQmyN+s6Il2QrCscLBvrURe/iH3PQqS+CzF3gHMhmW0r1bXMZIXoiQ6rQDnsSSSljU5ibuyVLIwkpmbmTk5vV3pZCUtN0uZOWkRx4BLW0qJVm6QeMSLEjJYZcw9kmTm1d6HBlpgHaljRj2v3u4PilXmCJlRBeSsq2xdFlXEYnIWY6h6izpfUWEzk85ehYU6ME9RKIybhim8Lvl8PvHe0Yek1HNX60MRT0QK/R4ol7qE3uO5sRBIlmY90uuYfVavJWLHhGfM73Jf2CsUdTmRl75GDlZyVqzNw+aE4eGvv2Ps6iXSKaTBZSKxrDFfMFUsVQYUDSHun//eJGiKA9H9yRNVeu2gTS/h4pbTIfm5XmIcMkKF5OMjKwJ5h3xnQsV/0SlYVxr9g+6g6QHydcooYPTlPSrqvEsAfKgWihokXFNODOyqjuWPHDGPL49g4gosepLB3bc5eUhqyaZzBR8X2zPU8pXRFj2Bbpr0iccbrhD0hyq+IfnRcNOPTc5+d0MFR/OdAdoxBb6IVYfsue6iaCbHlBRK+prR9xcD8ZLTqV+1G+TLy/HnA4EI64Z6TQrJMeF3k1VvamXdOGNLDkvmxLHHoxkSRZmTk6z8YvUIm/ICsWTlbLRPuAUJKS8yuptPZKxrAPcqjLk+lmzZijOI3rY6QIr6J1fqbLpwORIBhb9O0p4Zhxss2CKNreRHSw19FZRQ/DiuHrEBoBSJzPHK4U2aqe8zaSvotOnDDPShzbptE21AETrZoXmVGypqs0r9ezzMpX2LiQrKfeKm4PRvuSVq2Sv3C8qIs5/9wkMLy33PPf0ukRmXeiZqbpiNRAxqDwszf8DzVoI1UXOTlM1Z6snz2nyX52IfjU35MMxKcHccb1r8g18SKgXjltMtQgEDWpKbhuI5Sn60DxDZs3Wkra4NPyVBvMKXdTC0XSxNuIPpQMeBj1ZRqojnli0GfIVd6R1h2psCl02WksTaMHVxS9PY7iC4XJGp+uI5t9GcF6cefW6WB6qIVVhF7hHF4cU6Y9sTVTTlrzeSn2h3C9Egt/lOmXLa8VoZMhulQVHKN2a/JYGKyafZdjkUoHck0WQMtzCoWzrsc4VJyfb7cgKlwbFiEWXAkndYPmSPZ60lGIRPyjsWTcvEGqzcg+jI519RigudLN76OzWwyNXXWz9wCq8kvJ5X3JHtdhz01v9JJV7e+BY2ok0MYgfQbmE0dEJtpXIY0rEa2zZVptxOGHZIgrTrC3LACFXP0piwZnlj2ElizZ/kMNp3crRuyJbf+DV5bQj25JrBB+uk3LJ/JDD/iN07yjZDvQImfWJ5WLVOnzIcOCyJ7VGREdOK3OpvlQfD4XZqwyjlUPYpEsPkH8RduLDiH4OqD/xwAJzumDnZeMcL2jSlrHjQFanjXBgdfESMiGNymNp42+lq9HJ1YnzhpR60go4f5thzyjNxxzNTl0MJuDlai8WLFdXkwGpcpLqOe+0c3aGUBzqxjQ+qcfN9dqKzaEYid+T/nMJc6lcfZ1LZtWOTYeNOXSSqhMOM31MMQp9N60W7X4GOuyy5AWluyXnVsr2Cl4NWVtz1mXY2Ng19x2LnpXrApZ33KQpl6fogLF73o6VjSdiYuY+QOMp2EIY9avVAbw5Cv2UNNTBPQsgUpNxxjt59qeY5Ai7f6jcMeIIuRMf6HLEHfMzdv8tn2DEzeCeMuL0zQ4lXaJHPz9eXGbYyLCOJWOjiWqgkBuaKjM6GyR7M9j/rBssei7t1jSYICtPUGp0NPUTnvGxrGkgUbQQvTvjFj6j5AwWCWSDe/ci/yeMJ/n5Ad1TAXfFG31knaADF9b9Xar7zXPTGbo4W0dWIZCaxGqUpcXTKW7meTbFxEdVvkY2onWz2shwRrcVV1slmtgirF5GgE2esx/0yN8cUFbT3eHKKLtLC11WqwDn2zEmlzIY1S3J2exkZUEG8WGV+FQY1cCmy4vtUIhCOl3jDLboiZwIT+QYPJET4YkcgydyIjyRY/BEToQncgyeyInwRI7h20T4orCv+828a/AbXZh/gYj6614O8/Klv+IfIRK7j5fCHvCy//QIrk/k+LY3850+5dWJyFqfib+SSCzNvaNtmKsT+VY79RYkgt1E+dNq0P4Cg9VTrj67oP8Drk4Effytri5w+QT0wfY7Gxn+BSKqJXd0SXFJaFCBLzKRneOt7wzvnyFSiEwOFyhXT7Li5ojo8WAz3rQ3L2jTy8H49iSyiXih48UNy812os3NEUl5e9TrQAzvVXLpzREp3cddp2DmytsjwtvLX+OFbFaNbo9IEvE6ytvrR7ARMrk9IvGH7VX8IvZEaoMn8vq2J1IrPJHXtz2RWuGJvL7tidQKT+T1bU+kVngir2//M0RwGdTliOACLk/kr8PzRA5veyK1whN5fdsTqRVG7XDdVvvuCFarVZtvBIo922+vekbr11qsLiRt+tKx47RxY4/dGYjgjnHhV8tQ74FVnbT5ydqtdabJ9xX73nHCtHGOrevxWu7GcASp3FhD7nj6bj75plwlM5UbBf0VBQivz0HE8S2iX5bSvkIqt7rnyXy35il3F2scPj92HD7Z/Rm24MfKrttlc9M8grKkh8HmbsL3z3sHvDG52wwO3/orNs2yvbZnuMGLdNOt/WojwJv7lhsXH+5f9M5ryY+NcfnDUVj7vptfIxMe3PEDV9+w7jPVOjiAbxyGSZ+DB9+M4fgIjK3+0w7H1F88l+Vhyd0h5DvHzscnPAMTDw8PDw8PDw8PDw8PDw8PDw8PDw8PDw+P/w/8D0xbs1AHfMVJAAAAAElFTkSuQmCC")
        List <String> immagini,
        @NotEmpty(message = "email obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotNull(message = "tel obbligatorio")
        Long numeroTelefono,

        boolean caniAmmessi,
        @Column(length = 5000)
        @Size(max = 5000)
        String descrizione,
        boolean piscina,

        boolean animazione,

        boolean market,

        boolean ristorante,


        @Min(value = 0, message = "Il numero minimo di stelle è 1")
        @Max(value = 5, message = "Il numero massimo di stelle è 5")
       Integer stelle,
        @Column(length = 5000)
        String logo,


        @NotEmpty(message = "la password è obbligatoria")
        String password

){ public  NewCampeggioDTO{}


}
