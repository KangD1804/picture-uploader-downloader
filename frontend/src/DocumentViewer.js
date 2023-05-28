import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import {fetchDocumentText} from "./store";
const DocumentViewer = ({ documentText, loading, fetchDocumentText }) => {
    useEffect(() => {
        fetchDocumentText();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1>Document Viewer</h1>
            <p style={{fontSize:"40px"}}>{documentText}</p>
        </div>
    );
};

const mapStateToProps = (state) => ({
    documentText: state.documentText,
    loading: state.loading
});

const mapDispatchToProps = {
    fetchDocumentText
};

export default connect(mapStateToProps, mapDispatchToProps)(DocumentViewer);
