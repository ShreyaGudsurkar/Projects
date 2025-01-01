import { useState } from 'react';
import "./App.css";
import Header from './Header';
import Home from './Home';
import About from './About';
import DesignVault from "./DesignVault";
import Profile from './EditProfile.jsx';
import Footer from './Footer';
import profilePictureMapper from './profilePictureMapper.js';
import MySavedEdit from './MySavedEdit.jsx';
import SkipLinks from "./SkipLinks.jsx";

function App() {
    const [page, setPage] = useState('#home');
    const [profile, setProfile] = useState({
        username: '',
        profilePic: 'profilePic1',
    });
    const [showProfileSettings, setShowProfileSettings] = useState(false);
    const [savedEdits, setSavedEdits] = useState([]);

    function gotoPage(e) {
        e.preventDefault();
        setPage(e.target.hash);
        setShowProfileSettings(false);
    }

    function openProfileSettings() {
        setShowProfileSettings(true);
    }

    function closeProfileSettings() {
        setShowProfileSettings(false);
    }

    const toggleSaveEdit = (item) => {
        setSavedEdits((prev) => {

            const exists = prev.some((edit) => edit.id === item.id);

            if (exists) {

                const updatedEdits = prev.filter((edit) => edit.id !== item.id);
                console.log("Item found, removing:", item);
                console.log("Updated saved edits:", updatedEdits);
                return updatedEdits;
            } else {

                const updatedEdits = [...prev, item];
                console.log("Item not found, adding:", item);
                console.log("Updated saved edits:", updatedEdits);
                return updatedEdits;
            }
        });
    };




    const removeFromSaved = (item) => {
        setSavedEdits((prev) => prev.filter((edit) => edit.title !== item.title));
    };

    return (
        <div className="app">
            <SkipLinks />
            <Header
                gotoPage={gotoPage}
                profile={{
                    ...profile,
                    profilePicSrc: profilePictureMapper[profile.profilePic],
                }}
                openProfileSettings={openProfileSettings}
            />
            <main className="main-content" id="main-content">
                {showProfileSettings && (
                    <Profile
                        profile={profile}
                        setProfile={setProfile}
                        closeProfileSettings={closeProfileSettings}
                    />
                )}
                {!showProfileSettings && (
                    <>
                        {page === '#home' && <Home />}
                        {page === '#about' && <About gotoPage={gotoPage} />}
                        {page === '#designVault' && (
                            <DesignVault
                                savedEdits={savedEdits}
                                toggleSaveEdit={toggleSaveEdit}
                            />
                        )}
                        {page === '#mySavedEdit' && (
                            <MySavedEdit
                                savedEdits={savedEdits}
                                removeFromSaved={removeFromSaved}
                            />
                        )}
                    </>
                )}
            </main>
            <Footer />
        </div>
    );
}

export default App;
