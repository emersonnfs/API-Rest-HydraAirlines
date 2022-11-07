import React from "react";
import { useState } from "react";

export default function Cadastrar() {

    const [cliente, setCliente] = useState({
        nome: "",
        data: "",
        email: "",
        telefone: [{
            ddi: "",
            ddd: "",
            nrTel: ""
        }]
    });

    const handleChanges = (event) => {
        setCliente({ ...cliente, [event.target.name]: event.target.value });
    }
    
    const cadastrar= async (event) => {
        event.preventDefault();
    
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente),
        };
    
        const response = await fetch("http://localhost:8080/hydra-airlines/rest/cliente", requestOptions);
    
        const date = await response.json();
    
        if (date) {
            alert("Cliente cadastrado com sucesso!");
            window.location="/cliente";
        } else {
            alert("Erro ao cadastrar cliente!");
        }
    }
    return (
        <div>
            <div>
                <form onSubmit={cadastrar}>
                    <fieldset>
                        <legend>Cadastro de Clientes</legend>
                        <div>
                            <label htmlFor="nome">Nome</label>
                            <input type="text" 
                                   name="nome" 
                                   id="nome" 
                                   placeholder="Nome" 
                                   value={cliente.nome} 
                                   onChange={handleChanges} />
                        </div>
                        <div>
                            <label htmlFor="data">Data de Nascimento</label>
                            <input type="text" 
                                   name="data" 
                                   id="data" 
                                   placeholder="Data de Nascimento" 
                                   value={cliente.data} 
                                   onChange={handleChanges} />
                        </div>
                        <div>
                            <label htmlFor="email">Email</label>
                            <input type="email" 
                                   name="email" 
                                   id="email" 
                                   placeholder="Email" 
                                   value={cliente.email} 
                                   onChange={handleChanges} />
                        </div>
                        <fieldset>
                            <div>
                                <label htmlFor="ddi">DDI</label>
                                <input type="text" 
                                        name="ddi" 
                                        id="ddi" 
                                        placeholder="DDI" 
                                        value={cliente.telefone.ddi} 
                                        onChange={handleChanges} />
                            </div>
                            <div>
                                <label htmlFor="ddd">DDD</label>
                                <input type="text" 
                                    name="ddd" 
                                        id="ddd" 
                                        placeholder="DDD" 
                                        value={cliente.telefone.ddd} 
                                        onChange={handleChanges} />
                            </div>
                            <div>
                                <label htmlFor="nrTel">Telefone</label>
                                <input type="text" 
                                    name="nrTel" 
                                    id="nrTel" 
                                    placeholder="Telefone" 
                                    value={cliente.telefone.nrTel} 
                                    onChange={handleChanges} />
                            </div>
                        </fieldset>
                        <button>Cadastrar</button>
                    </fieldset>
                </form>
            </div>
        </div>
    );
}