import React from "react"
import { useEffect, useState } from "react"

export default function Clientes() {
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