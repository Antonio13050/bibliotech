import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { registerUser } from "../../service/ApiFunctions";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const NewUserRegistrationSchema = z.object({
    username: z.string().min(1, "Nome é obrigatório"),
    email: z.string().min(1, "E-mail é obrigatório").email("E-mail inválido"),
    // .superRefine((value, ctx) => {
    //     const userExists = users.find((user) => user.email === value);
    //     if (userExists) {
    //         return ctx.addIssue({
    //             code: "custom",
    //             message: "E-mail já cadastrado",
    //         });
    //     }
    // }),
    password: z
        .string()
        .min(1, "Senha é obrigatório")
        .superRefine((value, ctx) => {
            const regex = /^(?=.*[a-zA-Z])(?=.*\d).+/;
            if (!regex.test(value) || value.length < 8) {
                return ctx.addIssue({
                    code: "custom",
                    message:
                        "A senha deve conter no mínimo 8 caracteres, com letras e números",
                });
            }
        }),
});

export default function SignUp() {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm({
        resolver: zodResolver(NewUserRegistrationSchema),
    });

    const onSubmit = async (data) => {
        try {
            const result = await registerUser(data);
            toast.success("User created!");
        } catch (error) {
            toast.error(`Registration error : ${error}`);
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label>Nome:</label>
                <input type="text" {...register("username")} />
                <span>{errors.username?.message}</span>

                <label>E-mail:</label>
                <input type="email" {...register("email")} />
                <span>{errors.email?.message}</span>

                <label className="form-label">Senha:</label>
                <input
                    type="password"
                    className="form-control"
                    {...register("password")}
                />
                <span>{errors.password?.message}</span>

                <button type="submit" className="btn-form-new-user btn">
                    Cadastrar
                </button>
            </form>
            <ToastContainer />
        </div>
    );
}
