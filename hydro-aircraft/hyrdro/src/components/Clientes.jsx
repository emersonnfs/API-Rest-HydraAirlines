import React from "react"
import { useEffect, useState } from "react"

import Sem_Foto from "../Imgs/ImgsClientesHydro/Sem_Foto.jpg"
import Elon_Musk from "../Imgs/ImgsClientesHydro/Elon_Musk.jpg"
import Jeff_Bezos from "../Imgs/ImgsClientesHydro/Jeff_Bezos.jpg"
import Bernard_Arnault from "../Imgs/ImgsClientesHydro/Bernard_Arnault.jpg"
import Bill_Gates from "../Imgs/ImgsClientesHydro/Bill_Gates.jpg"
import Warren_Buffett from "../Imgs/ImgsClientesHydro/Warren_Buffett.jpg"
import Larry_Page from "../Imgs/ImgsClientesHydro/Larry_Page.jpg"
import Sergey_Brin from "../Imgs/ImgsClientesHydro/Sergey_Brin.jpg"
import Larry_Ellison from "../Imgs/ImgsClientesHydro/Larry_Ellison.jpg"
import Steve_Ballmer from "../Imgs/ImgsClientesHydro/Steve_Ballmer.jpg"
import Mukesh_Ambani from "../Imgs/ImgsClientesHydro/Mukesh_Ambani.jpg"
import Gautam_Adani from "../Imgs/ImgsClientesHydro/Gautam_Adani.jpg"
import Mike_Bloomberg from "../Imgs/ImgsClientesHydro/Mike_Bloomberg.jpg"
import Carlos_Slim from "../Imgs/ImgsClientesHydro/Carlos_Slim.jpg"
import Francoise_Bettencourt from "../Imgs/ImgsClientesHydro/Francoise_Bettencourt.png"
import Mark_Zuckerberg from "../Imgs/ImgsClientesHydro/Mark_Zuckerberg.jpg"
import Jim_Walton from "../Imgs/ImgsClientesHydro/Jim_Walton.jpg"
import Zhong_Shanshan from "../Imgs/ImgsClientesHydro/Zhong_Shanshan.jpg"
import Alice_Walton from "../Imgs/ImgsClientesHydro/Alice_Walton.jpg"
import Rob_Walton from "../Imgs/ImgsClientesHydro/Rob_Walton.jpg"
import Changpeng_Zhao from "../Imgs/ImgsClientesHydro/Changpeng_Zhao.jpg"


export default function Clientes() {
    const fotos = [Sem_Foto, 
                   Elon_Musk,
                   Jeff_Bezos,
                   Bernard_Arnault,
                   Bill_Gates,
                   Warren_Buffett,
                   Larry_Page,
                   Sergey_Brin,
                   Larry_Ellison,
                   Steve_Ballmer,
                   Mukesh_Ambani,
                   Gautam_Adani,
                   Mike_Bloomberg,
                   Carlos_Slim,
                   Francoise_Bettencourt,
                   Mark_Zuckerberg,
                   Jim_Walton,
                   Zhong_Shanshan,
                   Alice_Walton,
                   Rob_Walton,
                   Changpeng_Zhao
]

    const [clientes, setClientes] = useState([])

    useEffect(() => {
        fetch("http://localhost:8080/hydra-airlines/rest/cliente")
            .then((response) => response.json())
            .then((data) => {
                setClientes(data);
            })
            .catch(err => console.error(err));
    }, []);


    return (
        <div>
            <h1>Clientes</h1>
            <table>
                <thead>
                    <tr>
                        <th>Foto</th>
                        <th>Nome</th>
                        <th>Data</th>
                        <th>Email</th>
                        <tr>
                            <th>DDI</th>
                            <th>DDD</th>
                            <th>TELEFONE</th>
                        </tr>
                    </tr>
                </thead>
                <tbody>
                    {clientes.map((cliente) => (
                        <tr key={cliente.id}>
                            <td><img src={fotos[cliente.id ]} alt="Foto do Cliente" width="100" height="100" /></td>
                            <td>{cliente.nome}</td>
                            <td>{cliente.data}</td>
                            <td>{cliente.email}</td>
                            {cliente.telefone.map((telefone) => (
                                <tr key={telefone.id}>
                                    <td>{telefone.ddi}</td>
                                    <td>{telefone.ddd}</td>
                                    <td>{telefone.nrTel}</td>
                                </tr>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}