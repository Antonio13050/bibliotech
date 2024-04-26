import { Fragment, useState } from "react";
import { Dialog, Popover, Transition } from "@headlessui/react";
import { Bars3Icon, XMarkIcon } from "@heroicons/react/24/outline";
import { ModeToggle } from "../mode-toggle";
import { Link } from "react-router-dom";
import React from "react";
import { useAuth } from "../../context/AuthProvider";
import { useNavigate } from "react-router-dom";
import { Button } from "../ui/button";

export default function Header() {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

    const auth = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        auth.handleLogout();
        navigate("/login", {
            state: { message: " You have been logged out!" },
        });
    };

    return (
        <header className="">
            <nav
                className="mx-auto flex max-w-7xl items-center justify-between p-6 lg:px-8"
                aria-label="Global"
            >
                <div className="flex lg:flex-1">
                    <Link href="#" className="-m-1.5 p-1.5">
                        <span className="sr-only">Your Company</span>
                        <h1 className="text-3xl font-bold">Bibliotech</h1>
                    </Link>
                </div>

                <div className="flex lg:hidden gap-x-6">
                    <ModeToggle />
                    <button
                        type="button"
                        className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-800 dark:text-gray-200"
                        onClick={() => setMobileMenuOpen(true)}
                    >
                        <span className="sr-only">Open main menu</span>
                        <Bars3Icon className="h-6 w-6" aria-hidden="true" />
                    </button>
                </div>

                <Popover.Group className="hidden lg:flex lg:gap-x-12 lg:justify-end items-center">
                    <Popover className="relative">
                        <Transition
                            as={Fragment}
                            enter="transition ease-out duration-200"
                            enterFrom="opacity-0 translate-y-1"
                            enterTo="opacity-100 translate-y-0"
                            leave="transition ease-in duration-150"
                            leaveFrom="opacity-100 translate-y-0"
                            leaveTo="opacity-0 translate-y-1"
                        ></Transition>
                    </Popover>

                    <Link
                        to={"/"}
                        className="font-semibold leading-6 text-gray-800 dark:text-gray-200"
                    >
                        Home
                    </Link>
                    <Link
                        to={"/about"}
                        className="font-semibold leading-6 text-gray-800 dark:text-gray-200"
                    >
                        Sobre
                    </Link>
                    <Button onClick={handleLogout}>Logout</Button>
                    <ModeToggle />
                </Popover.Group>
            </nav>
            <Dialog
                as="div"
                className="lg:hidden"
                open={mobileMenuOpen}
                onClose={setMobileMenuOpen}
            >
                <div className="fixed inset-0 z-10" />
                <Dialog.Panel className="fixed inset-y-0 right-0 z-10 w-full overflow-y-auto bg-background dark:bg-background px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
                    <div className="flex items-center justify-between">
                        <a href="#" className="-m-1.5 p-1.5">
                            <span className="sr-only">Your Company</span>
                            <h1>Bibliotech</h1>
                        </a>
                        <button
                            type="button"
                            className="-m-2.5 rounded-md p-2.5 text-gray-800 dark:text-gray-200"
                            onClick={() => setMobileMenuOpen(false)}
                        >
                            <span className="sr-only">Close menu</span>
                            <XMarkIcon className="h-6 w-6" aria-hidden="true" />
                        </button>
                    </div>
                    <div className="mt-6 flow-root">
                        <div className="-my-6 divide-y divide-gray-500/10">
                            <div className="space-y-2 py-6">
                                <Link
                                    to={"/"}
                                    className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-800 hover:bg-gray-50 dark:text-gray-200 dark:hover:bg-gray-900"
                                >
                                    Home
                                </Link>
                                <Link
                                    to={"/about"}
                                    className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-800 hover:bg-gray-50 dark:text-gray-200 dark:hover:bg-gray-900"
                                >
                                    Sobre
                                </Link>
                                <div className="pt-5">
                                    <Button onClick={handleLogout}>
                                        Logout
                                    </Button>
                                </div>
                            </div>
                            {/* <div className="py-6">
                                <ModeToggle />
                            </div> */}
                        </div>
                    </div>
                </Dialog.Panel>
            </Dialog>
        </header>
    );
}
