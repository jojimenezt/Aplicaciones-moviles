package com.jojimenezt.reto10.Model;

public class LenguaNativa {
    /*
    * "nombre_de_lengua": "Gitanos-Rom",
        "descripci_n_de_lengua": "\"¿Quiénes son- Kon si von- los Rrom? son los\ndescendientes del milenario pueblo gitano, el cual\ntiene una serie de características propias que lo\ndiferencian de otros pueblos y de la sociedad\ncolombiana mestiza.\nLos gitanos colombianos hacen parte de la nación\ngitana que en el mundo ha compartido un estilo de\nvida con altos patrones de movilidad, que trascendía\nfronteras nacionales y no los aferraba a ningún\nterritorio de manera nómada. Para un Rrom\nencontrarse con otro de cualquier nación es hallar\nparte de su Nación. Los gitanos en el mundo han\npervivido como grupo poblacional gracias a la\nsostenibilidad de la cultura, a pesar de las diferentes\npersecuciones que sufrieron a través de la historia, y\nde los procesos de homogeinización propios de la\nglobalización.\"\n",
        "departamento": "N. SANTANDER",
        "familia_ling_stica": "ROMANÍ",
        "n_mero_de_habitantes": "4858",
        "n_mero_de_hablantes": "4220",
        "vitalidad": "Vulnerable"
    *
    * */
    public String nombre_de_lengua;
    public String descripci_n_de_lengua;
    public String departamento;
    public String familia_ling_stica;
    public int n_mero_de_habitantes;
    public int n_mero_de_hablantes;
    public String vitalidad;

    public LenguaNativa(String nombre_de_lengua,
            String descripci_n_de_lengua,
            String departamento,
            String familia_ling_stica,
            int n_mero_de_habitantes,
            int n_mero_de_hablantes, String vitalidad)
    {
        this.nombre_de_lengua = nombre_de_lengua;
        this.departamento = departamento;
        this.descripci_n_de_lengua = descripci_n_de_lengua;
        this.familia_ling_stica = familia_ling_stica;
        this.n_mero_de_habitantes = n_mero_de_habitantes;
        this.n_mero_de_hablantes = n_mero_de_hablantes;
        this.vitalidad = vitalidad;
    }

}
