import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { registerUser } from "../../service/ApiFunctions";
import { toast } from "sonner";

import { Button } from "@/components/ui/button";
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { PasswordInput } from "@/components/password-input";
import { Link, useNavigate } from "react-router-dom";

const NewUserRegistrationSchema = z.object({
    username: z.string().min(1, "Usuário é obrigatório"),
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
    const form = useForm({
        resolver: zodResolver(NewUserRegistrationSchema),
        defaultValues: {
            username: "",
            email: "",
            password: "",
        },
    });

    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        try {
            const result = await registerUser(data);
            console.log(result);
            toast("Usuário criado com sucesso", {
                description: `Bem vindo ${result.data.username}!`,
                position: "top-right",
                style: {
                    padding: "16px",
                    backgroundColor: "#1b5a1d",
                },
            });
            setTimeout(() => {
                navigate("/login", { replace: true });
            }, 3000);
        } catch (error) {
            setErrorMessage(error.message);
        }

        setTimeout(() => {
            setErrorMessage("");
        }, 3000);
    };

    return (
        <div className="h-screen w-screen">
            <div className="container h-screen w-screen mx-auto flex flex-col items-center justify-center">
                <div className="form-container border-2 p-8 rounded-lg lg:max-w-4/12 lg:w-4/12 h-fit w-[366px]">
                    <Form {...form}>
                        <form
                            onSubmit={form.handleSubmit(onSubmit)}
                            className="space-y-8"
                        >
                            <h1 className="text-3xl font-bold text-center">
                                Cadastre-se
                            </h1>
                            <FormField
                                control={form.control}
                                name="username"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Usuário</FormLabel>
                                        <FormControl>
                                            <Input
                                                placeholder="Digite seu usuário"
                                                {...field}
                                            />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="email"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>E-mail</FormLabel>
                                        <FormControl>
                                            <Input
                                                placeholder="Digite seu e-mail"
                                                {...field}
                                            />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="password"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Password</FormLabel>
                                        <FormControl>
                                            <PasswordInput
                                                id="current_password"
                                                placeholder="Digite sua senha"
                                                {...field}
                                            />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <div className="text-center">
                                {errorMessage && (
                                    <FormMessage>{errorMessage}</FormMessage>
                                )}
                            </div>
                            <div className="flex justify-center w-full">
                                <Button type="submit" className="sm:w-4/12">
                                    Cadastrar
                                </Button>
                            </div>
                        </form>
                    </Form>

                    <p className="text-center pt-5">
                        Já tem uma conta?
                        <Link to={"/login"} className="pl-2 hover:underline">
                            Clique aqui
                        </Link>
                    </p>
                </div>
            </div>
        </div>
    );
}
